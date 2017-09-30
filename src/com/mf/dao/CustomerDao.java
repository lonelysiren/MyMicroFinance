package com.mf.dao;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.utils.DateJsonValueProcessor;
import com.mf.utils.JdbcUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

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
		String debtData = "{\"creditcard\":{\"0\":{\"creditcard_name[]\":\"1\",\"creditcard_limit[]\":\"1\",\"creditcard_used[]\":\"1\"}},\"lingyong\":{\"0\":{\"lingyong_name[]\":\"2\",\"lingyong_amount[]\":\"2\"}},\"other\":{\"0\":{\"other_name[]\":\"3\",\"other_amount[]\":\"3\"}}}";
		String eData = "{\"creditcard\":{\"0\":{\"creditcard_id[]\":\"15\",\"creditcard_name[]\":\"23\",\"creditcard_limit[]\":\"23\",\"creditcard_used[]\":\"23\"}}}";

		JSONObject data = JSONObject.fromObject(customerData);
		JSONObject result = new JSONObject();
		int id = 0;
		// logger.info(object.getJSONObject(0));
		// logger.info(data);
		// JSONObject addCustomerContact = customerDao.addCustomerContact(data, "10");
		// JSONObject result = customerDao.editCustomerDbet(data, "10");
		// id = customerDao.addCustomer(data);
		//JSONObject findAll = customerDao.detail("9");
		String arrayData = "[{\"sex\":\"2\"},{\"sex\":\"3\"}]";
		JSONArray test = JSONArray.fromObject(arrayData);
		logger.info(test.size());
		logger.info(test);

	}

	public void close() {
		jdbcUtil.close();
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

	public String editCustomerInfo(String table_name, JSONObject data, String customer_id) throws SQLException {
		data.remove("harea");
		data.remove("hproper");
		data.remove("hcity");
		String result = super.editSql(table_name, data, "customer_id");
		return result;
	}

	public JSONObject editCustomerContact(JSONObject data, String customer_id) throws SQLException {
		JSONObject result = new JSONObject();
		if (data.has("contact_content")) {
			int contact_other_id = 0;
			String contact_content = data.getString("contact_content");
			data.remove("contact_content");
			params.add(contact_content);
			if (data.has("contact_other_id")) {
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
		while (keys.hasNext()) {
			String key = (String) keys.next();
			JSONObject contact = data.getJSONObject(key);
			if (contact.has("contact_id[]")) {
				super.editSql("customer_info_contact", contact, "contact_id");
			} else {
				if (isUserful(contact)) {
					contact.put("customer_id", customer_id);
					int index = super.addSql("customer_info_contact", contact);
					result.put(key, index);
				}
			}
		}
		return result;
	}

	public int addCustomerCompany(JSONObject data, String customer_id) throws SQLException {
		data.put("customer_id", customer_id);
		int CustomerCpmpany_id = 0;
		CustomerCpmpany_id = super.addSql("customer_info_company", data);
		return CustomerCpmpany_id;
	}
	
	public String editCustomerCompany(String table_name, JSONObject parameters, String requirement) throws SQLException {
		String result = super.editSql(table_name, parameters, requirement);
		return result;
	}

	public JSONObject editCustomerDbet(JSONObject data, String customer_id) throws SQLException {
		JSONObject result = new JSONObject();
		logger.info(data.toString());
		String table_name = "customer_info_debt_";
		Iterator<?> keys = data.keys();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			JSONObject debts = data.getJSONObject(key);
			Iterator<?> index_set = debts.keys();
			while (index_set.hasNext()) {
				String index = (String) index_set.next();
				JSONObject debt = debts.getJSONObject(index);
				if (debt.has(key + "_id[]")) {
					super.editSql(table_name + key, debt, key + "_id");
				} else {
					JSONObject out_index_set = new JSONObject();
					debt.put("customer_id", customer_id);
					int debt_id = super.addSql(table_name + key, debt);
					out_index_set.put(index, debt_id);
					result.accumulate(key, out_index_set);
				}
			}

		}
		return result;
	}

	public Boolean isUserful(JSONObject parameters) {
		if (parameters.isEmpty())
			return false;
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

	public JSONObject findAll(int page, int limit, int company_id) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		sql = "select manager_name,customer_name,customer_id,idcard_number,stauts,"
				+ "image1,image2,image3,amount,circle,check_date,check_stauts,customer_contract_id"
				+ " from customer_list where company_id = ? limit ? , ? ";
		params.add(company_id);
		if (page == 1) {
			params.add(0);
			params.add(limit);
		} else {
			params.add((page - 1) * limit);
			params.add(limit);
		}
		List<Map<String, Object>> list = null;
		try {
			list = jdbcUtil.findModeResult(sql, params);
			sql = " select count(1)  from customer_list where company_id = ? ";
			params.remove(1);
			params.remove(1);
			map = jdbcUtil.findSimpleResult(sql, params);
			maps.put("code", 0);
			maps.put("msg", "获取成功");
			maps.put("count", map.get("count(1)"));
			maps.put("data", list);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			jdbcUtil.close();
		}
		params.clear();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor());
		JSONObject jsonObject = JSONObject.fromObject(maps, jsonConfig);
		return jsonObject;
	}

	public JSONObject detail(String customer_id) throws SQLException {
		Map<String, Object> detail = new LinkedHashMap<String, Object>();
		sql = "SELECT\n" + "customer_info.customer_id,\n" + "customer_info.customer_name,\n" + "customer_info.sex,\n"
				+ "customer_info.age,\n" + "customer_info.marriage_status,\n" + "customer_info.idcard_type,\n"
				+ "customer_info.idcard_number,\n" + "customer_info.child_age,\n" + "customer_info.census_register,\n"
				+ "customer_info.census_register_detail,\n" + "customer_info.house_number,\n"
				+ "customer_info.house_status,\n" + "customer_info.house_rent,\n" + "customer_info.house_holder,\n"
				+ "customer_info.current_residence,\n" + "customer_info.native_type,\n"
				+ "customer_info.current_residence_phone,\n" + "customer_info.mobile_phone,\n"
				+ "customer_info.mobile_phone_age,\n" + "customer_info.mobile_phone_real_name,\n"
				+ "customer_info.education,\n" + "customer_info.graduate_school,\n" + "customer_info.account_number,\n"
				+ "customer_info.deposit_bank,\n" + "customer_info.remark,\n" + "customer_info.sales_account_manager\n"
				+ "FROM\n" + "customer_info where customer_id = ?";
		String sql2 = "SELECT " + "customer_info_contact.contact_id," + "customer_info_contact.relationship,"
				+ "customer_info_contact.contact_name," + "customer_info_contact.contact_mobile_phone,"
				+ "customer_info_contact.contact_company " + "FROM " + "customer_info_contact where customer_id = ?";
		String sql2_1 = "SELECT contact_other_id, contact_content FROM customer_info_contact_other where customer_id = ?";
		String sql3 = "SELECT " + "customer_info_debt_creditcard.creditcard_id,"
				+ "customer_info_debt_creditcard.creditcard_name," + "customer_info_debt_creditcard.creditcard_limit,"
				+ "customer_info_debt_creditcard.creditcard_used " + "FROM "
				+ "customer_info_debt_creditcard where customer_id = ?";
		String sql4 = "SELECT " + "customer_info_debt_lingyong.lingyong_id, "
				+ "customer_info_debt_lingyong.lingyong_name, " + "customer_info_debt_lingyong.lingyong_amount "
				+ "FROM " + "customer_info_debt_lingyong where customer_id = ?";
		String sql5 = "SELECT " + "customer_info_debt_other.debt_other_id, " + "customer_info_debt_other.other_name, "
				+ "customer_info_debt_other.other_amount " + "FROM " + "customer_info_debt_other where customer_id = ?";
		String sql6 = "SELECT customer_info_company.customer_company_id,customer_info_company.company_name,customer_info_company.department,customer_info_company.position,customer_info_company.compnay_address,customer_info_company.postal_code,customer_info_company.phone_number,customer_info_company.phone_number_extension,customer_info_company.phone_number_hr,customer_info_company.work_year,customer_info_company.salary,customer_info_company.accumulation_fund_account,customer_info_company.accumulation_fund_password,customer_info_company.company_type FROM customer_info_company where customer_id = ?";
		params.add(customer_id);
		Map<String, Object> customer_info = jdbcUtil.findSimpleResult(sql, params);
		Map<String, Object> company = jdbcUtil.findSimpleResult(sql6, params);
		Map<String, Object> contact_other = jdbcUtil.findSimpleResult(sql2_1, params);
		List<Map<String, Object>> contacts = jdbcUtil.findModeResult(sql2, params);
		List<Map<String, Object>> debt_creditcard = jdbcUtil.findModeResult(sql3, params);
		List<Map<String, Object>> debt_lingyong = jdbcUtil.findModeResult(sql4, params);
		List<Map<String, Object>> debt_other = jdbcUtil.findModeResult(sql5, params);
		detail.put("customer_info", customer_info);
		detail.put("company", company); 
		detail.put("contacts", contacts);
		detail.put("contact_other", contact_other);
		detail.put("debt_creditcard", debt_creditcard);
		detail.put("debt_lingyong", debt_lingyong);
		detail.put("debt_other", debt_other);
		JSONObject customer_detail = JSONObject.fromObject(detail);
		return customer_detail;
	}

	public JSONObject editCustomer(JSONObject data, String customer_id) throws SQLException {
		Iterator<?> keys = data.keys();
		Map<String, Object> map = new HashMap<String, Object>();
		while (keys.hasNext()) {
			String key = (String) keys.next();
			JSONObject obj;
			String table_name = "customer_info";
			switch (key) {
			case "info":
				obj = data.getJSONObject(key);
				String value = editCustomerInfo(table_name, obj, "customer_id");
				map.put(key, value);
				break;
			case "company":
				 obj = data.getJSONObject(key);
				table_name = table_name + "_" + key;
				if(obj.has("customer_"+key+"_id")) {
					String company = super.editSql(table_name, obj, "customer_"+key+"_id");
					map.put(key, company);
				}else {
					obj.accumulate("customer_id", customer_id);
					int company = super.addSql(table_name, obj);
					map.put(key, company);
				}
				;
				break;
			case "contact_other":
				 obj = data.getJSONObject(key);
				table_name = table_name + "_" + key;
				if(obj.has(key+"_id")) {
					String contact_other = super.editSql(table_name, obj, key+"_id");
					map.put(key, contact_other);
				}
				else {
					obj.accumulate("customer_id", customer_id);
					int contact_other = super.addSql(table_name, obj);
					map.put(key, contact_other);
				}
				break;
			default:
				//contact,debt_other....
				table_name = table_name + "_" + key;
				JSONArray jsonArray = data.getJSONArray(key);
					  for(int i=0;i<jsonArray.size();i++){
					    JSONObject jsonObj = jsonArray.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					    if(jsonObj.has(key+"_id")) {
							 String result = super.editSql(table_name, jsonObj, key+"_id");
							 logger.info(key+":修改成功");
							 map.put(key+i+"",result);
						}else {
							jsonObj.accumulate("customer_id", customer_id);
							 int result = super.addSql(table_name, jsonObj);
							 logger.info(key+":添加成功");
							 map.put(key+i,result);
						}
					  }
				break;
			}
		}
		 logger.info(map);
		return null;
	}

}
