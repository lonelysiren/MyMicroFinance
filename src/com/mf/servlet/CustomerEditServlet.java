package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.dao.CustomerDao;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerEditServlet
 */
@WebServlet("/CustomerEditServlet")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = LogManager.getLogger(CustomerEditServlet.class.getName());
	private Boolean editSql;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CustomerEditServlet() {
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
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		String customer_id = request.getParameter("customer_id");
		HttpSession session = request.getSession();
		CustomerDao customerDao = new CustomerDao();
		String parameter = request.getParameter("data");
		;
		JSONObject data = JSONObject.fromObject(parameter);
		JSONObject jresult = new JSONObject();
		String result = null;
		try {
			logger.info(data);
			switch (action) {
			case "customer_info":
				data.put("customer_id", customer_id);
				result = customerDao.editCustomerInfo(action, data, "customer_id");
				break;
			case "customer_info_contact":
				 jresult = customerDao.editCustomerContact(data, customer_id);
				break;
			case "customer_info_company":
				result = customerDao.editCustomerCompany(action, data, "customer_company_id");
				break;
			case "customer_info_debt":
				 jresult =	customerDao.editCustomerDbet(data, customer_id);
				break;
			case "customer":
				jresult = customerDao.editCustomer(data,customer_id);
			default:
				break;
			}
			if(jresult.isEmpty() != true) {
				response.getWriter().print(jresult);
				return;
			}
			if(result.isEmpty() != true) {
				response.getWriter().write(result);
				return;
			}
			logger.info(result);
		} catch (SQLException e) {
			logger.info(parameter);
			e.printStackTrace();
		} finally {
			customerDao.close();
		}

	}

}
