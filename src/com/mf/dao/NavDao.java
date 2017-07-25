package com.mf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.mf.entity.Nav;
import com.mf.utils.JdbcUtil;

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
			System.out.println(navs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return navs;
		
	}
	public static void main(String[] args) {
		NavDao navDao = new NavDao();
		navDao.NavBar();
	}
}
