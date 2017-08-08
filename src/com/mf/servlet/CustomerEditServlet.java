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

import com.mf.dao.UserDao;

/**
 * Servlet implementation class CustomerEditServlet
 */
@WebServlet("/CustomerEditServlet")
public class CustomerEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
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
		HttpSession session=request.getSession();
		UserDao userDao = new UserDao();
		String parameter;
		int id;
		switch (action) {
		case "check_id":
			String idcard_number = request.getParameter("idcard");
			String sales_account_manager = request.getParameter("sales_account_manager");
			int company = (int) session.getAttribute("company_id");
			String result = userDao.CheckId(idcard_number,sales_account_manager,company);
			response.getWriter().write(result);
			break;
		case "customer_info":
			 parameter = request.getParameter("customer_info");
			 id = userDao.addCustomer(parameter);
			response.getWriter().print(id);
			break;
		case "customer_relation_info":
			 parameter = request.getParameter("customer_info");
			 id = userDao.addCustomer(parameter);
			response.getWriter().print(id);
			break;
		case "customer_company_info":
			 parameter = request.getParameter("customer_info");
			 id = userDao.addCustomer(parameter);
			response.getWriter().print(id);
			break;
		default:
			break;
		}
		
	}

}
