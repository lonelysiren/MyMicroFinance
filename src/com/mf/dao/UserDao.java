package com.mf.dao;

import com.mf.utils.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.mf.entity.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserDao {
	
	 private JdbcUtil jdbcUtil = new JdbcUtil();
	 private String sql = null;
	 private  List<Object> params = new ArrayList<Object>();  
	 
	 
	 
	public UserDao() {
		super();

			 try {
				jdbcUtil.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		 
	}

	public Map<String, Object> login(String userName,String passWord) throws Exception{
	        sql = "SELECT nickname,role,stauts,company_id,name FROM company_info INNER JOIN user_info ON user_info.company_id = company_info.id where username = ? and password = ?";
	        params.add(userName); 
	        params.add(passWord);  
	        Map<String, Object> map = jdbcUtil.findSimpleResult(sql, params); 
	        params.clear();
	        if(map.size() !=0) {	        	
	        	return map;
	        } else {
	        	return null;
	        }
	        
	}
	
	public JSONArray findUserByCompany(int company_id) throws SQLException {
		sql = "select id,nickname from user_info where company_id = ?";
		params.add(company_id);
		List<Map<String, Object>> list = jdbcUtil.findModeResult(sql, params);
		JSONArray jsonObject = JSONArray.fromObject(list);
		return jsonObject;
	}
	
	public JSONObject findUserByCompanyId(int company_id,int pageIndex,int pageSize) throws SQLException {
		  Map<String,Object> maps = new LinkedHashMap<String,Object>(); 
		  Map<String,Object> map = new LinkedHashMap<String,Object>(); 
	        	  sql = "select user_info.id,username,nickname,stauts from user_info where company_id = ? limit ? , ? ";
	  	        params.add(company_id); 
	  	        if(pageIndex==1) {
	  	        	params.add(0);
	  	        	params.add(pageSize); 
	  	        }else {
	  	        		params.add((pageIndex-1) * pageSize);
	  	        		params.add(pageSize); 
	  	        }
	  	        List<Map<String, Object>> list = jdbcUtil.findModeResult(sql, params);	
	  			 maps.put("rel", true);
	  			 maps.put("msg", "获取成功");
	  			 maps.put("list", list);
	  			sql = " select count(1)  from user_info where company_id = ? ";
	  			System.out.println(params.size());
	  			params.remove(1);
	  			params.remove(1);
	  			map = jdbcUtil.findSimpleResult(sql,params);	
	  			 maps.put("count", map.get("count(1)"));
			   JSONObject jsonObject = JSONObject.fromObject(maps);
			   params.clear();
	        return jsonObject;
	   }
	  
	  public JSONObject findUserById(String id) throws SQLException {
		   sql = "select email,role,company_id from user_info where id = ? ";
		  params.add(id); 
		  Map<String, Object> user_info = jdbcUtil.findSimpleResult(sql, params );
		  JSONObject jsonObject = JSONObject.fromObject(user_info);
		  params.clear();
		  return jsonObject;
		}
	  
	  public Boolean updateByUserId(User user) throws SQLException {
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
		  Boolean flag =jdbcUtil.updateByPreparedStatement(sql, params);
		  params.clear();
			return flag;
		}
	  
	  public boolean deleteById(String id) throws SQLException {
		  sql = "delete from user_info where id = ?";
		  params.add(id);
		  Boolean flag = jdbcUtil.updateByPreparedStatement(sql, params);
		  params.clear();
			return flag;
		}
	  
	  public String CheckId(String idcard_number, String sales_account_manager, int company_id) throws SQLException {
		  sql = "select sales_account_manager_id from manager_relation where idcard_number = ? and company_id = ?";
		  params.add(idcard_number);
		  params.add(company_id);
		  String result = "0";
		
			List<Map<String, Object>> findModeResult = jdbcUtil.findModeResult(sql, params);
			if(!findModeResult.isEmpty()) {
				result = "1";
				for (Map<String, Object> map : findModeResult) {
					if(!map.get("sales_account_manager_id").toString().equals(sales_account_manager)) {
						result = "2";
						break;
					}
				}
			}
			return result;
	  }
	 public static void main(String[] args) throws SQLException {
	 
	
		
	 }

	public boolean addUser(User user) throws SQLException {
		  sql = "insert into user_info values(null,?,?,?,?,?,?,?)";
		  params.add(user.getUsername());
		  params.add(user.getPassword());
		  params.add(user.getNickname());
		  params.add(user.getEmail());
		  params.add(user.getStauts());
		  params.add(user.getRole());
		  params.add(user.getCompany());
		  Boolean flag =jdbcUtil.updateByPreparedStatement(sql, params);
		  params.clear();
			return flag;
	}

	

	

	
}
