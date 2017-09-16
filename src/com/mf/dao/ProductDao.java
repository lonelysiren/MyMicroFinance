package com.mf.dao;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.utils.JdbcUtil;

import net.sf.json.JSONObject;

public class ProductDao extends BaseDao {

	static Logger logger = LogManager.getLogger(ProductDao.class.getName());
	private JdbcUtil jdbcUtil = null;
	private String sql = null;
	private List<Object> params = null;

	public ProductDao() {
		super();
		this.jdbcUtil = super.jdbcUtil;
		this.params = super.params;
	}


	public static void main(String[] args) {
		String product_str = "{\"name\":\"3\",\"cycle\":\"3\",\"day_rate\":\"3\",\"week_rate\":\"3\",\"margin_loans\":\"3\",\"management_pay\":\"3\",\"other_pay\":\"3\",\"payment_method\":\"0\",\"remark\":\"3\",\"company_id\":\"1\"}";
		JSONObject product = JSONObject.fromObject(product_str);
		ProductDao productDao = new ProductDao();
		try {
			int addProduct = productDao.add(product);
			logger.info(addProduct);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public int add(JSONObject product) throws SQLException {
	//	product.put("day_rate", product.get("day_rate") + "‰");
	//	product.put("week_rate", product.get("week_rate") + "‰");
		int result = super.addSql("product_info", product);
		return result;

	}

	public String edit(JSONObject product) throws SQLException {
		// TODO Auto-generated method stub
		String result = super.editSql("product_info", product, "product_id");
		return result;
	}
	
	public int delete(JSONObject product) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public JSONObject paging(int page, int limit, int company_id) {
		Map<String, Object> maps = new LinkedHashMap<String, Object>();
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		sql = "select product_id,product_name,product_cycle,product_day_rate,product_week_rate,"
				+ "product_margin_loans,product_management_pay,product_other_pay,product_payment_method,product_isAdvance,product_remark"
				+ " from product_info where company_id = ? limit ? , ? ";
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
			
			sql = " select count(1)  from product_info where company_id = ? ";
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
		JSONObject jsonObject = JSONObject.fromObject(maps);
		return jsonObject;
	}
}
