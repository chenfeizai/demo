package com.demo.testxydcallbacknoticy;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class dopush
 */

/* 浏览器地址 */
// @WebServlet("/dopush")
public class Dopush extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print("get");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().println("post");
		String orderid = request.getParameter("order_id");
		Loan_return loan = new Loan_return();
		loan.loans(orderid);
		response.getWriter().println("已放款成功");
	}
}
