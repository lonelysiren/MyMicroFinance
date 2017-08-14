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
		UserDao userDao = new UserDao();
		String result = null;
		String parameter;
		int id;
		Map<String, Object> id_map;
		logger.info(action);
		switch (action) {
			case "customer_info":
				 parameter = request.getParameter("data");
				 id = userDao.editCustomer(parameter);
				response.getWriter().print(id);
				break;
			case "customer_relation_info":
				 parameter = request.getParameter("data");
				 id_map = userDao.editCustomerRelation(parameter,customer_id);
				 JSONObject info = JSONObject.fromObject(id_map);
				response.getWriter().print(info);
				break;
			case "customer_company_info":
				 parameter = request.getParameter("data");
				  result = userDao.editCustomerCompany(parameter,customer_id);
				response.getWriter().print(result);
				break;
			default:
				break;
		}
		
	}

}
