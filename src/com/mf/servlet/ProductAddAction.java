package com.mf.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ProductAddAction
 */
@WebServlet("/ProductAddAction")
public class ProductAddAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductAddAction() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 Enumeration<String> names = request.getParameterNames();
	        while (names.hasMoreElements()) {
	            String strings = (String) names.nextElement();
	            String[] parameterValues = request.getParameterValues(strings);
	            for (int i = 0;parameterValues!=null&&i < parameterValues.length; i++) {
	                System.out.println(strings+":"+parameterValues[i]+"\t");
	            }
	        }
	    }
	
}
