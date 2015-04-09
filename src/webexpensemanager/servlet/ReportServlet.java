package webexpensemanager.servlet;

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
import java.util.List;

public class ReportServlet extends HttpServlet {

	private static final String DATES_NOT_SET_MESSAGE = "Dates are not set. Using all time range.";

	@EJB
	HTMLWrapper htmlWrapper;

    @EJB
	Dates dates;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException{
		String categoryCheckboxes = htmlWrapper.wrapCategoryCheckBoxes(Categories.getCategoriesArray());
		request.setAttribute("categoryCheckboxes", categoryCheckboxes);
		request.setAttribute("earlydate", dates.dateHTMLinput.format(dates.early()));
		request.setAttribute("today", dates.todayHTML());
		request.getRequestDispatcher("reportRequest.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		boolean expenseInclude = (request.getParameter("Expenses") != null);
		boolean incomeInclude = (request.getParameter("Incomes") != null);
		Date startDate = getStartDate(request);
		Date endDate = getEndDate(request);
		boolean[] includeCategory = getCategoryCheckboxes(request);
		List<Expense> filteredList
				= Expenses.filter(expenseInclude, incomeInclude, startDate,
				endDate, includeCategory);
		Double total = Expense.total(filteredList);
		request.setAttribute("total", total);
		String filteredListTable = htmlWrapper.wrapExpenseTable(filteredList);
		request.setAttribute("filteredListTable", filteredListTable);
		String categoryCheckboxes = htmlWrapper.wrapCategoryCheckBoxes(Categories.getCategoriesArray());
		request.setAttribute("categoryCheckboxes", categoryCheckboxes);
		request.getRequestDispatcher("report.jsp").forward(request, response);
	}

	private boolean[] getCategoryCheckboxes(HttpServletRequest request){
		String[] categoriesArray = Categories.getCategoriesArray();
		int length = categoriesArray.length;
		String parameters[] = new String[length];
		boolean includeCategory[] = new boolean[length];
		for(int i = 0; i < length; i++){
			parameters[i] = request.getParameter(categoriesArray[i]);
			includeCategory[i] = (parameters[i] != null);
		}
	    return includeCategory;
	}

	private Date getStartDate(HttpServletRequest request){
		String time = request.getParameter("time");
		String startDateString = request.getParameter("startDate");
		Date startDate = dates.early();
		if (time != null)
			switch(time){
			case "today"    :
				startDate = dates.beginningOf("Day"); break;
			case "week"     :
				startDate = dates.beginningOf("Week"); break;
			case "month"    :
				startDate = dates.beginningOf("Month"); break;
			case "specify"  :
				try{
					startDate = dates.dateHTML.parse(startDateString);
				}catch(ParseException e){
					request.setAttribute("errorMessage", DATES_NOT_SET_MESSAGE);
				}
				break;
			}
		return startDate;
	}

	private Date getEndDate(HttpServletRequest request){
		String time = request.getParameter("time");
		String endDateString = request.getParameter("endDate");
		Date endDate = dates.now();
		if (time != null)
			if (time.equals("specify")){
				try{
					endDate = dates.dateHTML.parse(endDateString);
				}catch(ParseException e){
					request.setAttribute("errorMessage", DATES_NOT_SET_MESSAGE);
				}
			}
		return endDate;
	}
}
