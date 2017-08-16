package com.mf.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.utils.JdbcUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CustomerDao extends BaseDao {
	static Logger logger = LogManager.getLogger(CustomerDao.class.getName());
	private JdbcUtil jdbcUtil = null;
	private String sql = null;
	private List<Object> params = null;

	public CustomerDao() {
		super();
		this.jdbcUtil = super.jdbcUtil;
		this.params = super.params;
	}

	public static void main(String[] args) throws SQLException {
		CustomerDao customerDao = new CustomerDao();
		String customerData = "{\"customer_name\":\"吴一凡\",\"sex\":\"0\",\"age\":\"23\",\"marriage_status\":\"2\",\"idcard_type\":\"1\",\"idcard_number\":\"431003199408136018\",\"child_age\":\"12\",\"census_register\":\"安徽省-安庆市-大观区\",\"harea\":\"大观区\",\"hproper\":\"安庆市\",\"hcity\":\"安徽省\",\"census_register_detail\":\"安徽省-安庆市-大\",\"house_number\":\"123\",\"house_status\":\"1\",\"house_rent\":\"123\",\"house_holder\":\"123\",\"current_residence\":\"安徽省-安庆市-大观区-安徽省-安庆市-大\",\"native_type\":\"0\",\"current_residence_phone\":\"123\",\"mobile_phone\":\"13762567348\",\"mobile_phone_age\":\"\",\"mobile_phone_real_name\":\"0\",\"education\":\"2\",\"graduate_school\":\"\",\"account_number\":\"123\",\"deposit_bank\":\"123\",\"remark\":\"\",\"sales_account_manager\":\"1\"}";
		String contacData = "{\"contact_content\":\"\",\"Object\":[{\"relationship[]\":\"1\",\"contact_name[]\":\"123\",\"contact_mobile_phone[]\":\"123\",\"contact_company[]\":\"213\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"}]}";
		JSONObject data = JSONObject.fromObject(contacData);
		JSONArray object = data.getJSONArray("Object");
		//logger.info(object.getJSONObject(0));
		//logger.info(data);
		 outerloop:  
			    for (int i=0; i < 5; i++) {  
			      for (int j=0; j < 5; j++) {  
			        if (i >1) {  
			          System.out.println("Breaking");  
			          continue outerloop;  
			        }  
			        System.out.println(i + " " + j);  
			      }  
			      System.out.println("外层");  
			    }  
			    System.out.println("Done");  
			  
	}

	public String CheckId(JSONObject data, int company_id) {
		sql = "select sales_account_manager_id from manager_relation where idcard_number = ? and company_id = ?";
		params.add(data.getString("idcard"));
		params.add(company_id);
		String result = "0";
		List<Map<String, Object>> findModeResult = null;
		try {
			findModeResult = jdbcUtil.findModeResult(sql, params);
			if (!findModeResult.isEmpty()) {
				result = "1";
				for (Map<String, Object> map : findModeResult) {
					if (!map.get("sales_account_manager_id").toString()
							.equals(data.getString("sales_account_manager"))) {
						result = "2";
						break;
					}
				}
			}
		} catch (SQLException e) {
			result = "3";
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		return result;
	}

	public int addCustomer(JSONObject data) throws SQLException {
		int Customer_id = 0;
		data.remove("harea");
		data.remove("hproper");
		data.remove("hcity");
		Customer_id = super.addSql("customer_info", data);
		return Customer_id;
	}

	public int editCustomer(JSONObject data) {
		return 0;
	}

	public Map<Object, Object> addCustomerContact(JSONObject data, String customer_id) throws SQLException {
		String content = data.getString("contact_content");
		JSONObject result = new JSONObject();
		
		JSONArray jsonArray = data.getJSONArray("Object");
		for (Object object : jsonArray) {
			JSONObject parameters = (JSONObject) object;
			Iterator<?> iterator = parameters.keys();
			while(iterator.hasNext()) {
				String key = (String) iterator.next();
				String value = parameters.getString(key);
			}
			parameters.put("customer_id", customer_id);
			int contact_id = super.addSql("customer_info_contact", parameters);
			result.accumulate("contact_id", contact_id);
		}
		
		if(true != content.trim().isEmpty()) {
			sql = "insert into customer_info_contact_other (contact_content,customer_id) values(?,?)";
			params.add(content);
			params.add(customer_id);
			int content_other_id = jdbcUtil.addByPreparedStatement(sql, params);
			result.put("content_other_id", content_other_id);
		}
		return null;
	}

	public Map<String, Object> addCustomerRelation(JSONObject data, String customer_id) throws SQLException {
		int relation_id = 1;
		int parmas_id = 0;
		sql = "insert into customer_info_contact values(?,?,?,?,?)";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Iterator<?> iterator = data.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = data.getString(key);
			if (value.trim().isEmpty()) {
				continue;
			}
			params.add(value);
			if (key.equals("relationship_other")) {
				String sql2 = "insert into customer_info_contact_other values(?,?)";
				params.add(customer_id);
				map.put("other_contact_id-6", jdbcUtil.addByPreparedStatement(sql2, params));
				break;
			}
			if (parmas_id == 4) {
				parmas_id = 0;
				params.clear();
				relation_id++;
				continue;
			}
			if (parmas_id == 3) {
				if (data.has("contact_id-" + relation_id)) {
					continue;
				}
				params.add(customer_id);
				map.put("contact_id-" + relation_id, jdbcUtil.addByPreparedStatement(sql, params));
				parmas_id = 0;
				relation_id++;
				params.clear();
				continue;
			}
			parmas_id++;
		}
		return map;
	}

	public Map<Object, Object> addCustomerRelationSimple(JSONObject parameters, String customer_id)
			throws SQLException {
		JSONObject parameter = new JSONObject();
		logger.info(parameters.toString());
		Map<Object, Object> result = new LinkedHashMap<Object, Object>();
		String index = "1";
		int result_id = 0;
		Iterator<?> iterator = parameters.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			String[] split = key.split("-");
			String num = split[1];
			String colunm_name = split[0];
			if (value.trim().isEmpty()) {
				continue;
			}
			parameter.put("customer_id", customer_id);
			if (num.equals(index)) {
				parameter.put(colunm_name.trim(), value);
				logger.info(colunm_name + ":" + value);
			} else {
				result_id = super.addSql("customer_info_contact", parameter);
				result.put(Integer.parseInt(index), result_id);
				index = split[1].trim();
				parameter.clear();
				parameter.put(colunm_name, value);
				logger.info(colunm_name + ":" + value);
				if (num.equals("6")) {
					parameter.put("customer_id", customer_id);
					result_id = super.addSql("customer_info_contact_other", parameter);
					result.put("contact_other_id-6", result_id);
					parameter.clear();
				}
			}
		}
		System.out.println(result.toString());
		return result;

	}

	public int editCustoemrRelation(JSONObject parameters) throws SQLException {
		JSONObject parameter = new JSONObject();
		Iterator<?> iterator = parameters.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			String[] split = key.split("-");
			parameter.put(key, value);
			if (split[0].equals("contact_id")) {
				super.editSql("customer_info_contact", parameter, "contact_id");
				parameter.clear();
			} else if (split[0].equals("other_contact_id")) {
				super.editSql("customer_info_contact_other", parameter, "contact_other_id");
				parameter.clear();
			}
		}
		return 1;
	}

	public int addCustomerCompany(JSONObject data, String customer_id) throws SQLException {
		data.put("customer_id", customer_id);
		int CustomerCpmpany_id = 0;
		CustomerCpmpany_id = super.addSql("customer_info_company", data);
		return CustomerCpmpany_id;
	}

	public Map<String, Object> editCustomerRelation(String parameter, String customer_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
