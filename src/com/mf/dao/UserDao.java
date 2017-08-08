package com.mf.dao;

import com.mf.utils.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mf.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

	private JdbcUtil jdbcUtil = new JdbcUtil();
	private String sql = null;
	private List<Object> params = new ArrayList<Object>();

	public UserDao() {
		super();
		try {
			jdbcUtil.getConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Map<String, Object> login(String userName, String passWord) {
		sql = "SELECT nickname,role,stauts,company_id,name FROM company_info INNER JOIN user_info ON user_info.company_id = company_info.id where username = ? and password = ?";
		params.add(userName);
		params.add(passWord);
		Map<String, Object> map = null;
		try {
			map = jdbcUtil.findSimpleResult(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		return map;

	}

	public JSONArray findUserByCompany(int company_id) {
		sql = "select id,nickname from user_info where company_id = ?";
		params.add(company_id);
		List<Map<String, Object>> list = null;
		try {
			list = jdbcUtil.findModeResult(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		JSONArray jsonObject = JSONArray.fromObject(list);
		return jsonObject;
	}

	public JSONObject findUserByCompanyId(int company_id, int pageIndex, int pageSize) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		sql = "select user_info.id,username,nickname,stauts from user_info where company_id = ? limit ? , ? ";
		params.add(company_id);
		if (pageIndex == 1) {
			params.add(0);
			params.add(pageSize);
		} else {
			params.add((pageIndex - 1) * pageSize);
			params.add(pageSize);
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

	public JSONObject findUserById(String id) {
		sql = "select email,role,company_id from user_info where id = ? ";
		params.add(id);
		Map<String, Object> user_info = null;
		try {
			user_info = jdbcUtil.findSimpleResult(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		JSONObject jsonObject = JSONObject.fromObject(user_info);
		params.clear();
		return jsonObject;
	}

	public Boolean updateByUserId(User user) {
		// TODO Auto-generated method stub
		sql = "UPDATE user_info SET username = ?, password = ?,nickname = ?,email = ?,stauts = ?,role = ?,company = ? where id = ?  ";
		params.add(user.getUsername());
		params.add(user.getPassword());
		params.add(user.getNickname());
		params.add(user.getEmail());
		params.add(user.getStauts());
		params.add(user.getRole());
		params.add(user.getCompany());
		params.add(user.getId());
		Boolean flag = false;
		try {
			flag = jdbcUtil.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		return flag;
	}

	public boolean deleteById(String id)  {
		sql = "delete from user_info where id = ?";
		params.add(id);
		Boolean flag = false;
		try {
			flag = jdbcUtil.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		return flag;
	}

	public String CheckId(String idcard_number, String sales_account_manager, int company_id) {
		sql = "select sales_account_manager_id from manager_relation where idcard_number = ? and company_id = ?";
		params.add(idcard_number);
		params.add(company_id);
		String result = "0";
		List<Map<String, Object>> findModeResult = null;
		try {
			findModeResult = jdbcUtil.findModeResult(sql, params);
			if (!findModeResult.isEmpty()) {
				result = "1";
				for (Map<String, Object> map : findModeResult) {
					if (!map.get("sales_account_manager_id").toString().equals(sales_account_manager)) {
						result = "2";
						break;
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			result = "3";
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		
		return result;
	}

	public static void main(String[] args) throws SQLException {
  UserDao userDao = new UserDao();

  String parameter = "{\"name\":\"吴一凡\",\"sex\":\"0\",\"age\":\"22\",\"marriage_status\":\"1\",\"idcard_type\":\"1\",\"idcard_number\":\"431003199408136018\",\"child_age\":\"23\",\"census_register\":\"安徽省-安庆市-大观区\",\"harea\":\"大观区\",\"hproper\":\"安庆市\",\"hcity\":\"安徽省\",\"census_register_detail\":\"家里\",\"house_number\":\"431003199408136018\",\"house_status\":\"1\",\"house_rent\":\"\",\"house_holder\":\"431003199408136018\",\"current_residence\":\"安徽省-安庆市-大观区-家里\",\"native_type\":\"0\",\"current_residence_phone\":\"\",\"mobile_phone\":\"13762567348\",\"mobile_phone_age\":\"\",\"mobile_phone_real_name\":\"0\",\"education\":\"1\",\"graduate_school\":\"家里\",\"account_number\":\"家里\",\"deposit_bank\":\"家里\",\"remark\":\"\",\"sales_account_manager\":\"1\"}";
  userDao.addCustomer(parameter);
	}

	public boolean addUser(User user)  {
		sql = "insert into user_info values(null,?,?,?,?,?,?,?)";
		params.add(user.getUsername());
		params.add(user.getPassword());
		params.add(user.getNickname());
		params.add(user.getEmail());
		params.add(user.getStauts());
		params.add(user.getRole());
		params.add(user.getCompany());
		Boolean flag = false;
		try {
			flag = jdbcUtil.updateByPreparedStatement(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		return flag;
	}

	public int addCustomer(String parameter) {
		JSONObject customer = JSONObject.fromObject(parameter);
		int id = 0;
		customer.remove("harea");
		customer.remove("hproper");
		customer.remove("hcity");
		sql = "insert into customer_info values (null"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",?,?,?,?,?"
				+ ",now(),null)";
		try {
			Iterator<?> iterator = customer.keys();
			while(iterator.hasNext()){
			        String	key = (String) iterator.next();
			        String value = customer.getString(key);
			        params.add(value);
			}
			
			 id = jdbcUtil.addByPreparedStatement(sql, params);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		return id;
	}

}
