package webexpensemanager.servlet;

import webexpensemanager.DAO.Factory;
import webexpensemanager.logic.Categories;
import webexpensemanager.logic.Expense;
import webexpensemanager.logic.Expenses;
import webexpensemanager.ejb.Dates;
import webexpensemanager.ejb.HTMLWrapper;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

public class WebExpenseManager extends HttpServlet{

    @EJB
	Factory factory;

    @EJB
	HTMLWrapper htmlWrapper;

    @EJB
	Dates dates;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String last5 = htmlWrapper.wrapExpenseTable(Expenses.last5());
		request.setAttribute("last5", last5);
		request.setAttribute("todaysExpenses", todaysExpenses());
		request.setAttribute("thisMonthExpenses", thisMonthExpenses());
		request.setAttribute("thisWeekExpenses", thisWeekExpenses());
		request.setAttribute("thisMonthBalance", thisMonthBalance());
		request.setAttribute("totalBalance", totalBalance());
		String selectCategory = htmlWrapper.wrapSelect(Categories.getCategoriesArray(), "category");
		request.setAttribute("selectCategory", selectCategory);
		request.setAttribute("today", dates.todayHTML());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

    public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String valueString = request.getParameter("addExpenseValue");
		String description = request.getParameter("addExpenseDescription");
		String dateStr = request.getParameter("addDate");
		String category = request.getParameter("category");
		boolean isIncome = (request.getParameter("addIncome") != null);
		Date date;
		double value=0;
		try{
			value = Double.parseDouble(valueString);
			if ((isIncome && (value < 0)) || ((!isIncome) && (value > 0)))
				value *= -1;
			date=dates.dateHTML.parse(dateStr);
			Expenses.addExpense(new Expense(value, description, date, category));
		}catch(NumberFormatException e){
			request.setAttribute("errorMessage", "A Number must be entered in Expense Value field.");
		}catch (ParseException e) {
			Expenses.addExpense(new Expense(value, description, dates.now(), category));
		}
		doGet(request, response);
	}

	public void init(){
		Factory.setInstance(factory);
		Categories.readCategoriesFromDB("", "");
		Expenses.readExpensesFromDB(-1, null);
	}

	double todaysExpenses(){
		return Expense.total(
				Expenses.filter(
						true, false, dates.beginningOf("Day"), dates.now(), Categories.allTrueCategory()));
	}

	double thisMonthExpenses(){
		return Expense.total(
				Expenses.filter(
						true, false, dates.beginningOf("Month"), dates.now(), Categories.allTrueCategory()));
	}

	double thisWeekExpenses(){
		return Expense.total(
				Expenses.filter(
						true, false, dates.beginningOf("Week"), dates.now(), Categories.allTrueCategory()));
	}

	double thisYearExpenses(){
		return Expense.total(
				Expenses.filter(
						true, false, dates.beginningOf("Year"), dates.now(), Categories.allTrueCategory()));
	}

	double thisMonthBalance(){
		return Expense.total(
				Expenses.filter(
						true, true, dates.beginningOf("Month"), dates.now(), Categories.allTrueCategory()));
	}

	double totalBalance(){
		return Expense.total(Expenses.getAllExpenses());
	}
}
