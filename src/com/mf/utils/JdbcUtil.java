package com.mf.utils;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

import net.sf.json.JSONObject;

public class JdbcUtil {

	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;
private static DataSource dataSource = null;
	
	 public  void close(){  
         if(resultSet!=null){  
             try {  
            	 resultSet.close();  
             } catch (SQLException e) {  
             }  
         }  
         if(pstmt!=null){  
             try {  
            	 pstmt.close();  
             } catch (SQLException e) {  
             }  
         }  
   
         if(connection!=null){  
             try {  
            	 connection.close();  
             } catch (SQLException e) {  
             }  
         }  
     }  
	
	static {
		dataSource = new ComboPooledDataSource("mvcApp");
	}


	public void getConnection() throws SQLException {
		connection = dataSource.getConnection();
	}

	public boolean updateByPreparedStatement(String sql, List<Object> params) throws SQLException {
		boolean flag = false;
		int result = -1;
		pstmt = connection.prepareStatement(sql);
		
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}
	
	public int addByPreparedStatement(String sql, List<Object> params) throws SQLException {
		int id = 0 ;
		pstmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				
				pstmt.setObject(index++, params.get(i));
			}
		}
		
		 pstmt.executeUpdate();
		 resultSet = pstmt.getGeneratedKeys(); 
		if(resultSet.next()){  
            id = resultSet.getInt(1);  
        }  
		return id;
	}
	
	public Map<String, Object> findSimpleResult(String sql, List<Object> params) throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		
		resultSet = pstmt.executeQuery();// ���ز�ѯ���
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	public List<Map<String, Object>> findModeResult(String sql, List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}
		return list;
	}

	public <T> T findSimpleRefResult(String sql, List<Object> params, Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			// ͨ��������ƴ���һ��ʵ��
			resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true); // ��javabean�ķ���Ȩ��
				field.set(resultObject, cols_value);
			}
		}
		return resultObject;

	}

	public <T> List<T> findMoreRefResult(String sql, List<Object> params, Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			// ͨ��������ƴ���һ��ʵ��
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true); // ��javabean�ķ���Ȩ��
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		JdbcUtil jdbcUtils = new JdbcUtil();
		jdbcUtils.getConnection();

		/******************* �� *********************/
		/*
		 * String sql =
		 * "insert into userinfo (username, pswd) values (?, ?), (?, ?), (?, ?)";
		 * List<Object> params = new ArrayList<Object>(); params.add("С��");
		 * params.add("123xiaoming"); params.add("����"); params.add("zhangsan");
		 * params.add("����"); params.add("lisi000"); try { boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params); System.out.println(flag); }
		 * catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */

		/******************* ɾ *********************/
		// ɾ������Ϊ�����ļ�¼
		/*
		 * String sql = "delete from userinfo where username = ?"; List<Object> params =
		 * new ArrayList<Object>(); params.add("С��"); boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 */

		/******************* �� *********************/
		// ������Ϊ���ĵ��������
		/*
		 * String sql = "update userinfo set pswd = ? where username = ? "; List<Object>
		 * params = new ArrayList<Object>(); params.add("lisi88888"); params.add("����");
		 * boolean flag = jdbcUtils.updateByPreparedStatement(sql, params);
		 * System.out.println(flag);
		 */

		/******************* �� *********************/
		// �����÷����ѯ�����¼
		/*
		 * String sql2 = "select * from userinfo "; List<Map<String, Object>> list =
		 * jdbcUtils.findModeResult(sql2, null); System.out.println(list);
		 */

		// ���÷����ѯ ������¼
		/*
		 * String sql = "select * from user_info where username = ? "; List<Object>
		 * params = new ArrayList<Object>(); params.add("6666"); User user; try { user =
		 * jdbcUtils.findSimpleRefResult(sql, params, User.class);
		 * System.out.println(user.getUsername());
		 * System.out.println(user.getPassword()); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */
		String sql = "select * from user_info where username = ? and password = ? ";
		List<Object> params = new ArrayList<Object>();
		params.add("6666");
		params.add("666");
		List<Map<String, Object>> list = jdbcUtils.findModeResult(sql, params);
		System.out.println(list.size());

	}

}
