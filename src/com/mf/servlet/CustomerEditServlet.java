package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.mf.dao.CustomerDao;
import com.mf.dao.UserDao;

import net.sf.json.JSONArray;
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		String customer_id = request.getParameter("customer_id");
		HttpSession session=request.getSession();
		CustomerDao customerDao = new CustomerDao();
		String parameter = request.getParameter("data");;
		JSONObject parameters = JSONObject.fromObject(parameter);
		String result = null;
		logger.info(action);
		try {
			switch (action) {
				case "customer_info":
					parameters.put("customer_id", customer_id);
					result = customerDao.editSql(action, parameters, "customer_id");
					response.getWriter().write(result);
					break;
				case "customer_info_contact":
					 JSONObject editCustoemrContact = customerDao.editCustoemrContact(parameters,customer_id);
					 response.getWriter().print(editCustoemrContact);
					break;
				case "customer_info_company":
						result = customerDao.editSql(action, parameters, "customer_company_id");
						response.getWriter().write(result);
					break;
				default:
					break;
			}
		}catch (SQLException e) {
			logger.info(parameter);
			e.printStackTrace();
		}
		
	}

}
