package com.mf.dao;

import com.mf.utils.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mf.entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {

	 static Logger logger = LogManager.getLogger(UserDao.class.getName());
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

	public String CheckId(String parameter, int company_id) {
		sql = "select sales_account_manager_id from manager_relation where idcard_number = ? and company_id = ?";
		JSONObject info = JSONObject.fromObject(parameter);
		params.add(info.getString("idcard"));
		params.add(company_id);
		String result = "0";
		List<Map<String, Object>> findModeResult = null;
		try {
			findModeResult = jdbcUtil.findModeResult(sql, params);
			if (!findModeResult.isEmpty()) {
				result = "1";
				for (Map<String, Object> map : findModeResult) {
					if (!map.get("sales_account_manager_id").toString().equals(info.getString("sales_account_manager"))) {
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

  String parameter = "{\"relationship_1\":\"1\",\"relationship_name_1\":\"6666\",\"relationship_phone_1\":\"66\",\"relationship_company_1\":\"666\",\"relationship_2\":\"2\",\"relationship_name_2\":\"66\",\"relationship_phone_2\":\"666\",\"relationship_company_2\":\"6666\",\"relationship_3\":\"1\",\"relationship_name_3\":\"5555\",\"relationship_phone_3\":\"555\",\"relationship_company_3\":\"55\",\"relationship_4\":\"1\",\"relationship_name_4\":\"4444\",\"relationship_phone_4\":\"444\",\"relationship_company_4\":\"44\",\"relationship_5\":\"1\",\"relationship_name_5\":\"3333\",\"relationship_phone_5\":\"333\",\"relationship_company_5\":\"333\",\"relationship_other\":\"5555\",\"sales_account_manager\":\"1\"}";
 Map<String, Object> ret = userDao.addCustomerRelation(parameter,"10");



 logger.info(ret.toString());
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

	public Map<String, Object> addCustomerRelation(String parameter, String customer_id) {
		JSONObject customer_relation = JSONObject.fromObject(parameter);
		logger.info(parameter);
		int relation_id = 1 ;
		int parmas_id = 0;
		sql = "insert into customer_info_contact values(null,?,?,?,?,?)";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		try {
			Iterator<?> iterator = customer_relation.keys();
			while(iterator.hasNext()){
			        String	key = (String) iterator.next();
			        String value = customer_relation.getString(key);
			        logger.info(key+":"+value);
			        if(value.trim().isEmpty()) {
			        	continue;
			        }
			        params.add(value);
			       if(key.equals( "relationship_other") ) {
			    	   String sql2 = "insert into customer_info_contact_other values(null,?,?)";
			    	   params.add(customer_id);
			    	 	map.put("id_"+ relation_id, jdbcUtil.addByPreparedStatement(sql2, params));
			    	 	continue;
			       }
			        if(parmas_id == 3 ) {
			        	params.add(customer_id);
			        	map.put("id_"+ relation_id, jdbcUtil.addByPreparedStatement(sql, params));
			        	 parmas_id = 0;
			        	 relation_id++;
			        	 params.clear();
			        	 continue;
			        }
			        parmas_id++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return map;
	}

	public Map<String, Object> addCustomerCompany(String parameter, String customer_id) {
		return null;
		// TODO Auto-generated method stub
	}

}
