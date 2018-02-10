package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mf.dao.CustomerDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerRepayDetail
 */
@WebServlet("/customer_repay")
public class CustomerRepayDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerRepayDetail() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		String customer_id = request.getParameter("customer_id");
		String idcard_number = request.getParameter("idcard_number");
		String customer_name = request.getParameter("customer_name");
		CustomerDao customerDao = new CustomerDao();
		try {
			JSONObject repayDetail = customerDao.getRepayDetail(customer_id);
			Map<String, Object> repay = new HashMap<String, Object>();
			JSONArray data = repayDetail.getJSONArray("data");
			Double amount = 0d;
			Double otherfee = 0d;
			Double realfee = 0d;
			if (data.size() > 0) {
				for (int i = 0; i < data.size(); i++) {
					JSONObject job = data.getJSONObject(i); // 遍历 jsonarray 数组，把每一个对象转成 json 对象
					amount = amount + job.getDouble("bj");
					otherfee = otherfee + job.getDouble("allfee") - job.getDouble("bj") - job.getDouble("lx") - job.getDouble("fee");
					job.put("wfzn", job.getDouble("yfzn")-job.getDouble("sfzn"));
					if(!job.getString("repay_day").isEmpty()) {
						realfee = realfee + job.getDouble("ysbj")+job.getDouble("ysfy")+job.getDouble("sfzn");
					}
				}
			}
			repay.put("customer_name", customer_name);
			repay.put("idcard_number", idcard_number);
			repay.put("amount", amount);
			repay.put("circle", data.size());
			repay.put("realfee", realfee);
			repay.put("otherfee", otherfee);
			request.setAttribute("repay", JSONObject.fromObject(repay));
			request.setAttribute("data", repayDetail.getJSONArray("data"));
			request.getRequestDispatcher("/lib/temp/detail-repay.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			customerDao.close();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String customer_id = request.getParameter("customer_id");
		CustomerDao customerDao = new CustomerDao();
		try {
			JSONObject repayDetail = customerDao.getRepayDetail(customer_id);
			response.getWriter().println(repayDetail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			customerDao.close();
		}
	}

}
