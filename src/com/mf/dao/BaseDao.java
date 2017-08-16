package com.mf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
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

	/**
	 * 根据传过来的json对象 生成添加sql语句
	 * @param table_name
	 * @param parameters
	 * @param params
	 * @return 
	 * @throws SQLException 
	 * 
	 */
	public int  addSql(String table_name, JSONObject parameters) throws SQLException {
		sql = "INSERT INTO " + table_name;
		column_name = " (";
		values = " values(";
		iterator = parameters.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			if (value.trim().isEmpty()) {//空值不添加
				continue;
			}
			if (iterator.hasNext()) {
			column_name += key+",";	
			values += "?,";
			} else {
				column_name += key +")";
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
	
	public String delteSql(String table_name,String requirement, String value ) throws SQLException {
		
		sql = "DELETE FROM " + table_name + " WHERE + "+ column_name + "= ? ";
		params.add(value);
		boolean result = jdbcUtil.updateByPreparedStatement(sql, params);
		return  result?"success":"faild";
		
	}
	
	public String editSql(String table_name, JSONObject parameters,String requirement ) throws SQLException {
		 sql = "UPDATE "+table_name +" SET";
		 column_name = " ";
		 values ="where "+requirement +" = ? ";
		 iterator = parameters.keys();
			Boolean isfirst = true;
			while(iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			if (key.equals(requirement)) {
				params.add(value);
				break;
			}
			column_name += key;
			if(isfirst) {
				column_name += "=?";
			}else if(iterator.hasNext()) {
				column_name += "=?,";
			}
			else {
				column_name += "=?) ";
			}
			params.add(value);
		}
		sql = sql + column_name + values;
		Boolean result = jdbcUtil.updateByPreparedStatement(sql, params);
		 return  result?"success":"faild";
	}
}
