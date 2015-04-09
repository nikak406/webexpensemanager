package webexpensemanager.servlet;

import webexpensemanager.logic.Expenses;
import webexpensemanager.ejb.Dates;
import webexpensemanager.ejb.HTMLWrapper;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExpenseServlet extends HttpServlet {

	@EJB
	HTMLWrapper htmlWrapper;

	@EJB
	Dates dates;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String number = request.getParameter("number");
		try{
			int n = Integer.parseInt(number);
			Expenses.removeExpense(n);
		}catch(NumberFormatException e){
			request.setAttribute("errorMessage", "A number must be entered in Remove Expense field");
		}
		request.getRequestDispatcher("redirect.jsp").forward(request, response);
	}
}
