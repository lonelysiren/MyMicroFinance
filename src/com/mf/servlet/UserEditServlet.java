package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mf.dao.UserDao;
import com.mf.entity.User;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class UserEditServlet
 */
@WebServlet("/UserEditServlet")
public class UserEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserDao userDao = new UserDao();
		String id = request.getParameter("id");
		JSONObject info = userDao.findUserById(id);
		System.out.println(info.toString());
		response.getWriter().write(info.toString());
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String del_id = request.getParameter("del");
		boolean result = false;
		UserDao userDao = new UserDao();
		if(del_id == null) {
			String id = request.getParameter("id");
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String nickname = request.getParameter("nickname");
			String email = request.getParameter("email");
			String stauts = request.getParameter("stauts");
			String role = request.getParameter("role");
			String company_id = request.getParameter("company_id");
			User user = new User();
			user.setId(Integer.parseInt(id));
			user.setUsername(username);
			user.setPassword(password);
			user.setNickname(nickname);
			user.setEmail(email);
			user.setStauts(stauts);
			user.setRole(role);
			user.setCompany(company_id);
			if((Integer.parseInt(id))==0) {
				result = userDao.addUser(user);
			}else {
				 result  = userDao.updateByUserId(user);
			}
			 if(result) {
				 response.getWriter().write("ok");
			 }
		} else {
			result = userDao.deleteById(del_id);
			if(result) {
				 response.getWriter().write("ok");
			}
		}
		
		
		
		
		
	}

}
