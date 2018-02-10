package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.dao.CustomerDao;
import com.mf.dao.ProductDao;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerVerifyAction
 */
@WebServlet("/CustomerVerifyAction")
public class CustomerVerifyAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(CustomerVerifyAction.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerVerifyAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int company_id = Integer.parseInt(request.getParameter("company_id"));
		String customer_id = request.getParameter("customer_id");
		CustomerDao customerDao = new CustomerDao();
		ProductDao productDao = new ProductDao();
		try {
			JSONObject detail_verify = customerDao.verify(customer_id);
			JSONArray products = productDao.detail(company_id);
			request.setAttribute("detail_verify", detail_verify);
			request.setAttribute("customer_id", customer_id);
			request.setAttribute("products", products);
			request.getRequestDispatcher("/lib/temp/detail-verify.jsp").forward(request, response);  
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			customerDao.Close();
			productDao.Close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String parameter = request.getParameter("detail");
		JSONObject data = JSONObject.fromObject(parameter);
		CustomerDao customerDao = new CustomerDao();
		logger.info(parameter);
		try {
			String result = customerDao.editCustomerContract(data);
			response.getWriter().write(result);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			customerDao.Close();
		}
	}

}
