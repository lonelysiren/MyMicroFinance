package com.mf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.utils.JdbcUtil;

import net.sf.json.JSONObject;

public class BaseDao {
	static Logger logger = LogManager.getLogger(BaseDao.class.getName());
	protected JdbcUtil jdbcUtil = new JdbcUtil();
	protected List<Object> params = new ArrayList<Object>();
	private String sql;
	private String column_name;
	private String values;
	private Iterator<?> iterator;

	public BaseDao() {
		super();
		try {
			jdbcUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void Close() {
		jdbcUtil.close();
	}

	/**
	 * 根据传过来的json对象 生成添加sql语句
	 * 
	 * @param table_name
	 * @param parameters
	 * @param params
	 * @return
	 * @throws SQLException
	 * 
	 */
	public int addSql(String table_name, JSONObject parameters) throws SQLException {
		sql = "INSERT INTO " + table_name;
		column_name = " (";
		values = " values(";
		iterator = parameters.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			if (key.trim().contains("[]")) {
				key = key.replace("[]", "");
			}
			if (value.trim().isEmpty()) {// 空值不添加
				continue;
			}
			if (iterator.hasNext()) {
				column_name += key + ",";
				values += "?,";
			} else {
				column_name += key + ")";
				values += "?)";
			}
			params.add(value);
		}
		sql = sql + column_name + values;
		logger.info(sql);
		int id = jdbcUtil.addByPreparedStatement(sql, params);
		params.clear();
		return id;
	}

	/**
	 * @param column
	 * @param table_name
	 * @param parameters
	 * @return
	 * @throws SQLException
	 */
	public int addSql(String column, String table_name, JSONObject parameters) throws SQLException {
		sql = "INSERT INTO " + table_name;
		column_name = " (";
		values = " values(";
		iterator = parameters.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			if (value.trim().isEmpty()) {// 空值不添加
				continue;
			}
			params.add(value);
			if (key.trim().contains("_id")) {
				column_name += key + ")";
				values += "?)";
				continue;
			}
			column_name += column + "_" + key + ",";
			values += "?,";
		}
		sql = sql + column_name + values;
		logger.info(sql);
		int id = jdbcUtil.addByPreparedStatement(sql, params);
		params.clear();
		return id;
	}

	public String delteSql(String table_name, String requirement, String value) throws SQLException {

		sql = "DELETE FROM " + table_name + " WHERE + " + requirement + "= ? ";
		params.add(value);
		boolean result = jdbcUtil.updateByPreparedStatement(sql, params);
		return result ? "success" : "faild";

	}

	public String editSql(String table_name, JSONObject parameters, String requirement) throws SQLException {
		sql = "UPDATE " + table_name + " SET";
		column_name = " ";
		values = " where " + requirement + " = ? ";
		iterator = parameters.keys();
		String req_value = null;
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			if (key.trim().contains("[]")) {
				key = key.replace("[]", "");
			}

			if (key.equals(requirement)) {
				req_value = value;
			} else {
				params.add(value);
				column_name = column_name + key + "=?,";
			}
			if (false == iterator.hasNext()) {
				column_name = column_name.substring(0, column_name.length() - 1);
				break;
			}
		}
		params.add(req_value);
		sql = sql + column_name + values;
		logger.info(sql);
		logger.info(params.toString());
		Boolean result = jdbcUtil.updateByPreparedStatement(sql, params);
		params.clear();
		return result ? "success" : "faild";
	}

	public JSONObject pagingSql(String table_name, int page, int limit, int id) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		sql = "select user_info.id,username,nickname,stauts from user_info where company_id = ? limit ? , ? ";
		params.add(id);
		if (page == 1) {
			params.add(0);
			params.add(limit);
		} else {
			params.add((page - 1) * id);
			params.add(limit);
		}
		List<Map<String, Object>> list = null;
		try {
			list = jdbcUtil.findModeResult(sql, params);
			maps.put("rel", true);
			maps.put("msg", "获取成功");
			maps.put("list", list);
			sql = " select count(1)  from user_info where company_id = ? ";
			params.remove(1);
			params.remove(1);
			map = jdbcUtil.findSimpleResult(sql, params);
			maps.put("count", map.get("count(1)"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		JSONObject jsonObject = JSONObject.fromObject(maps);
		return jsonObject;
	}
}
