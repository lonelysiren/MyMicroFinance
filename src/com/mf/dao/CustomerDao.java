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
		String contacData = "{\"contact_content\":\"\",\"Object\":[{\"relationship[]\":\"1\",\"contact_name[]\":\"123\",\"contact_mobile_phone[]\":\"123\",\"contact_company[]\":\"213\"},{\"relationship[]\":\"123\",\"contact_name[]\":\"123\",\"contact_mobile_phone[]\":\"123\",\"contact_company[]\":\"123\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"},{\"relationship[]\":\"\",\"contact_name[]\":\"\",\"contact_mobile_phone[]\":\"\",\"contact_company[]\":\"\"}]}";
		JSONObject data = JSONObject.fromObject(contacData);
		JSONArray object = data.getJSONArray("Object");
		// logger.info(object.getJSONObject(0));
		// logger.info(data);
		// JSONObject addCustomerContact = customerDao.addCustomerContact(data, "10");
		// logger.info(addCustomerContact);
		JSONObject result = new JSONObject();
		result.put("contact_id[]", "252");
		if (result.getInt("contact_id[]") >= 0) {// 是否已经创建联系人
			result.remove("contact_id[]");
			if (customerDao.isUserful(result)) {
				System.out.println("vaild");
			}else {
				System.out.println("111");
			}
				
		}
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

	public String editCustomer(String table_name,JSONObject data, String customer_id) throws SQLException {
		data.remove("harea");
		data.remove("hproper");
		data.remove("hcity");
		String result = super.editSql(table_name, data, "customer_id");
		return result;
	}

	public JSONObject editCustomerContact(JSONObject data, String customer_id) throws SQLException {
		JSONObject result = new JSONObject();
		logger.info(data.toString());
			if (data.has("contact_content")) {
				int contact_other_id = 0;
				String contact_content = data.getString("contact_content");
				data.remove("contact_content");
				params.add(contact_content);
				if (data.has("contact_other_id") ) {
					String id = data.getString("contact_other_id");
					data.remove("contact_other_id");
					params.add(id);
					sql = "UPDATE customer_info_contact_other set contact_content = ? where contact_other_id = ?";
					jdbcUtil.updateByPreparedStatement(sql, params);
				} else if (false == contact_content.trim().isEmpty()) {
					sql = "INSERT INTO customer_info_contact_other (contact_content,customer_id) values(?,?)";
					params.add(customer_id);
					contact_other_id = jdbcUtil.addByPreparedStatement(sql, params);
					result.put("contact_other_id", contact_other_id);
				}
				params.clear();
				
			}
		Iterator<?> keys = data.keys();
		while(keys.hasNext()) {
			String key = (String) keys.next();
			JSONObject contact = data.getJSONObject(key);
			if(contact.has("contact_id[]")){
				super.editSql("customer_info_contact", contact, "contact_id");
			}else {
				if (isUserful(contact)) {
					contact.put("customer_id", customer_id);
					int index = super.addSql("customer_info_contact", contact);
					result.put(key, index);
				}
			}
		}
		logger.info(result);
		return result;
	}

	public int addCustomerCompany(JSONObject data, String customer_id) throws SQLException {
		data.put("customer_id", customer_id);
		int CustomerCpmpany_id = 0;
		CustomerCpmpany_id = super.addSql("customer_info_company", data);
		return CustomerCpmpany_id;
	}

	public Boolean isUserful(JSONObject parameters) {
		if(parameters.isEmpty()) return false;
		Iterator<?> iterator = parameters.keys();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = parameters.getString(key);
			if (value.trim().isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
