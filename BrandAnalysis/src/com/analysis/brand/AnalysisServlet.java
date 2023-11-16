package com.analysis.brand;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AnalysisServlet
 */
public class AnalysisServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AnalysisServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String brandname = req.getParameter("brand").toUpperCase();
		String useremail = req.getParameter("email");
		if (DatabaseOperations.userRegister(useremail, brandname) && DatabaseOperations.getRatings(brandname)) {
			DateChangeTracker.startAnalysis();
		}
		else
		{
			System.out.println("out");
		}
		RequestDispatcher rd = req.getRequestDispatcher("index.html");
		rd.include(req, res);
	}

}
