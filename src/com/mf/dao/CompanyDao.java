package com.mf.dao;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.utils.JdbcUtil;

public class CompanyDao extends BaseDao {
	static Logger logger = LogManager.getLogger(CompanyDao.class.getName());
	private JdbcUtil jdbcUtil = null;
	private String sql = null;
	private List<Object> params = null;

	public CompanyDao() {
		super();
		this.jdbcUtil = super.jdbcUtil;
		this.params = super.params;
	}

	public void upload(String qrcode, String company_id) {
		// TODO Auto-generated method stub
		
	}
}
