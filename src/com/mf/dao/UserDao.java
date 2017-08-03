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
	        sql = "select * from user_info where username = ? and password = ?";
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
	
	public JSONArray findUserByCompany(String company) throws SQLException {
		sql = "select id,nickname from user_info where company = ?";
		params.add(company);
		List<Map<String, Object>> list = jdbcUtil.findModeResult(sql, params);
		JSONArray jsonObject = JSONArray.fromObject(list);
		return jsonObject;
	}
	
	public JSONObject findUserByCompany(String company,int pageIndex,int pageSize) throws SQLException {
		
		  Map<String,Object> maps = new LinkedHashMap<String,Object>(); 
		  Map<String,Object> map = new LinkedHashMap<String,Object>(); 
	        
	        	  sql = "select id,username,nickname,stauts from user_info where company = ? limit ? , ? ";
	  	        params.add(company); 
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
	  			 
	  			sql = " select count(1)  from user_info where company = ? ";
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
		   sql = "select email,role,company from user_info where id = ? ";
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
	  
	  public void CheckId(String idcard_number) {
		  
	  }
	 public static void main(String[] args) throws SQLException {
	 
		UserDao userDao = new UserDao();
		userDao.deleteById("5");
		
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
