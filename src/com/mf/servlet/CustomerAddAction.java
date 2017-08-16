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
import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class CustomerAddAction
 */
@WebServlet("/CustomerAddAction")
public class CustomerAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 static Logger logger = LogManager.getLogger(CustomerEditServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerAddAction() {
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
		JSONObject data = JSONObject.fromObject(parameter);
		int id;
		Map<Object, Object> id_map;
		try {
				switch (action) {
					case "check_id":
						int company = (int) session.getAttribute("company_id");
						String result = customerDao.CheckId(data,company);
						response.getWriter().write(result);
						break;
					case "customer_info":
						id = customerDao.addCustomer(data);
						response.getWriter().print(id);
						break;
					case "customer_info_contact":
						 id_map = customerDao.addCustomerContact(data,customer_id);
						 JSONObject info = JSONObject.fromObject(id_map);
						response.getWriter().print(info);
						break;
					case "customer_info_company":
						int customer_company_id = customerDao.addCustomerCompany(data,customer_id);
						response.getWriter().print(customer_company_id);
						break;
					default:
						break;
				}
		}catch(SQLException e){
			logger.info(parameter);
			e.printStackTrace();
		}
	}

}
