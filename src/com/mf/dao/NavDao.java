package com.mf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mf.entity.Nav;
import com.mf.utils.JdbcUtil;

import net.sf.json.JSONArray;

public class NavDao {
	private JdbcUtil jdbcUtil = new JdbcUtil();
	 private String sql = null;
	 private  List<Object> params = new ArrayList<Object>();  
	 
	 
	 
	public NavDao() {
		super();

			 try {
				jdbcUtil.getConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		 
	}
	
	public List<Map<String, Object>> NavBar(){
		sql = "select * from navs";
		List<Map<String, Object>> navs = null;
		try {
			navs = jdbcUtil.findModeResult(sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return navs;
		
	}
	public static void main(String[] args) {
		NavDao Dao = new NavDao();
		List<Map<String, Object>> navBar = Dao.NavBar();
		List<Map<String, Object>> delList = new ArrayList<Map<String, Object>>();
	
		
		//System.out.println(navBar.toString());
		navBar.removeAll(delList);
		JSONArray nav = JSONArray.fromObject(navBar);
	
	}
}
