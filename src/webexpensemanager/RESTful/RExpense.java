package webexpensemanager.RESTful;

import webexpensemanager.ejb.Dates;
import webexpensemanager.logic.Expense;
import webexpensemanager.logic.Expenses;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Path("expense")
public class RExpense {

	@EJB
	Dates dates;

	@GET
	@Path("{id}")
	@Produces({"text/xml", "text/json"})
	public Expense getExpense(@PathParam("id") int n){
		return Expenses.getExpense(n);
	}

	@GET
	@Produces({"text/xml", "text/json"})
	public List<Expense> getExpenseList(){
		return Expenses.getAllExpenses();
	}

	@DELETE
	@Path("{id}")
	public void removeExpense(@PathParam("id") int n){
		Expenses.removeExpense(n);
	}

	@PUT
	@Consumes("application/x-www-form-urlencoded")
	public void addExpense(@FormParam("value") double value,
						   @FormParam("category") String category,
						   @FormParam("description") String description,
						   @FormParam("date") String dateStr){
		Date date;
		try {
			date = dates.dateRUS.parse(dateStr);
		} catch (ParseException e) {
			date = dates.now();
		}
		Expense expense = new Expense(value, description, date, category);
		Expenses.addExpense(expense);
	}
}
