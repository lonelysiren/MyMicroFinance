package com.mf.servlet;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mf.dao.UserDao;
import com.mf.entity.User;
import com.mf.utils.JsonUtil;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		// TODO Auto-generated method stub
		String userName = request.getParameter("userName");
		String passWord = request.getParameter("passWord");
		UserDao userDao = new UserDao();
		try {
			Map<String, Object> user_info = userDao.login(userName,passWord);
			if(user_info != null && user_info.get("stauts").toString().equals("0")) {
				HttpSession session=request.getSession();
				session.setAttribute("role",user_info.get("role"));
				session.setAttribute("company_id",user_info.get("company_id"));
				 String company=URLEncoder.encode((String) user_info.get("name"), "utf-8"); 
				 String nickname=URLEncoder.encode((String) user_info.get("nickname"), "utf-8"); 
				 Cookie c_company = new Cookie("company",company);
				 Cookie c_nickname = new Cookie("name",nickname);
				 response.addCookie(c_company);
				 response.addCookie(c_nickname);
			}else {
				 response.getWriter().write("error");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
