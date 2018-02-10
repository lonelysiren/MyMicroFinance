package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mf.dao.CustomerDao;
import com.mf.dao.UserDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerDetailAction
 */
@WebServlet("/CustomerDetailAction")
public class CustomerDetailAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerDetailAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int company_id = Integer.parseInt(request.getParameter("company_id"));
		String customer_id = request.getParameter("customer_id");
		CustomerDao customerDao = new CustomerDao();
		UserDao userDao = new UserDao();
		try {
			JSONObject customer_detail = customerDao.detail(customer_id);
			JSONObject customer_info = customer_detail.getJSONObject("customer_info");
			JSONObject company =customer_detail.getJSONObject("company");
			JSONArray contacts =customer_detail.getJSONArray("contacts");
			JSONObject contact_other =customer_detail.getJSONObject("contact_other");
			JSONArray debt_creditcard =customer_detail.getJSONArray("debt_creditcard");
			JSONArray debt_lingyong =customer_detail.getJSONArray("debt_lingyong");
			JSONArray debt_other =customer_detail.getJSONArray("debt_other");
			JSONArray user_list = userDao.findUserByCompany(company_id);
			request.setAttribute("info", customer_info);
			request.setAttribute("company", company);
			request.setAttribute("contacts", contacts);
			request.setAttribute("contact_other", contact_other);
			request.setAttribute("debt_creditcard", debt_creditcard);
			request.setAttribute("debt_lingyong", debt_lingyong);
			request.setAttribute("debt_other", debt_other);
			request.setAttribute("user_list", user_list);
			request.getRequestDispatcher("/lib/temp/detail-customer.jsp").forward(request, response);  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			customerDao.close();
			userDao.Close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		CustomerDao customerDao = new CustomerDao();
		try {
			JSONObject customer_detail = customerDao.detail(id);
			response.getWriter().print(customer_detail);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			customerDao.close();
		}
	}

}
