package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mf.dao.UserDao;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/SelectServlet")
public class SelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SelectServlet() {
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
		// TODO Auto-generated method stub
		String data = request.getParameter("data");
		switch (data) {
		case "sales_account_manager":
			HttpSession session = request.getSession();
			int company_id = (int) session.getAttribute("company_id");
			UserDao userDao = new UserDao();
			try {
				JSONArray findUserByCompany = userDao.findUserByCompany(company_id);
				response.getWriter().write(findUserByCompany.toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
			break;
		case "marriage_status":
			response.sendRedirect("lib/datas/marriage_status.json");
			break;
		case "house_status":
			response.sendRedirect("lib/datas/house_status.json");
			break;
		case "idcard_type":
			response.sendRedirect("lib/datas/idcard_type.json");
			break;
		case "education":
			response.sendRedirect("lib/datas/education.json");
			break;
		case "relationship":
			response.sendRedirect("lib/datas/relationship.json");
			break;
		case "company_type":
			response.sendRedirect("lib/datas/company_type.json");
			break;
		default:
			break;
		}
	
	}

}
