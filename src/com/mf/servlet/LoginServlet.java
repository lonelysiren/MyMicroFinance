package com.mf.servlet;

import java.io.IOException;
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

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	  static Logger logger = LogManager.getLogger(LoginServlet.class.getName());
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		UserDao userDao = new UserDao();
		Map<String, Object> user_info = userDao.login(userName, passWord);
		
		if(user_info.isEmpty()) {
			response.getWriter().write("error");
		} else if(user_info.get("stauts").toString().equals("0")) {
			response.getWriter().write("disabled");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("id", user_info.get("id"));
			session.setAttribute("role", user_info.get("role"));
			session.setAttribute("company_id", user_info.get("company_id"));
			session.setAttribute("company_name", user_info.get("company_name"));
			session.setAttribute("nickname", user_info.get("nickname"));
		}
	

	}

}
