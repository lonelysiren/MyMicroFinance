package com.mf.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mf.dao.CustomerDao;

/**
 * Servlet implementation class Contract_set
 */
@WebServlet("/contract_set")
public class ContractSetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContractSetServlet() {
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
		CustomerDao customerdao = new CustomerDao();
		String customer_id = request.getParameter("customer_id");
		String op = request.getParameter("op");
		String result = "error";
		try {
			if (op.equals("qianding")) {
				result = customerdao.contractQianding(customer_id)?"合同签订成功":"合同签订失败";
			} else if (op.equals("fangkuan")) {
				result = customerdao.contractFangkuan(customer_id)?"放款设置成功":"放款设置失败";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			
		} catch (ParseException e) {
			e.printStackTrace();
		}finally {
			customerdao.close();
		}
		response.getWriter().write(result);
	}

}
