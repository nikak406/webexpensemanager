package webexpensemanager.logic;

import webexpensemanager.DAO.ExpenseDAO;
import webexpensemanager.DAO.Factory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Expenses {

	static private List<Expense> expenses = new ArrayList<>();

	public static List<Expense> getAllExpenses(){
		return expenses;
	}

	public static int getListSize(){
		return Expenses.getAllExpenses().size();
	}

	static public Expense getExpense(int i){
		List<Expense> allExpenses = Expenses.getAllExpenses();
		for(Expense exp : allExpenses){
			if (exp.getNumber() == i)
				return exp;
		}
		return null;
	}

	public static void readExpensesFromDB(int removeNumber, Expense expense){
		try {
			if (removeNumber > 0) {
				Expense expByID = Factory.getInstance().getExpenseDAO().getExpenseById(removeNumber);
				Factory.getInstance().getExpenseDAO().deleteExpense(expByID);
			}
			if (expense != null)
				Factory.getInstance().getExpenseDAO().addExpense(expense);
			Factory factory = Factory.getInstance();
			ExpenseDAO expenseDAO = factory.getExpenseDAO();
			expenses = expenseDAO.getAllExpense();
		}
		catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL Exception (Expenses): " + e);
		}
	}

	static public void addExpense(Expense expense){
		readExpensesFromDB(-1, expense);
	}

	static public void removeExpense(int i){
		List<Expense> allExpenses = Expenses.getAllExpenses();
		Expense expense = null;
		for (Expense exp : allExpenses){
			if (exp.getNumber() == i) {
				expense = exp;
			}
		}
		if (expense != null)
			readExpensesFromDB(i, null);
	}

	static public List<Expense> filter(boolean expensesIncluded, boolean incomesIncluded,
						 Date startDate, Date endDate, boolean[] includeCategory){
		String[] categoriesNames = Categories.getCategoriesArray();
		List<Expense> resultList = new ArrayList<>();
		List<Expense> allExpenses = Expenses.getAllExpenses();
		for (Expense exp : allExpenses){
			if ((!expensesIncluded) && (exp.getValue() < 0)
					|| ((!incomesIncluded) && (exp.getValue() > 0))
					|| (exp.getDate().before(startDate))
					|| (exp.getDate().after(endDate)))
				continue;
			for (int i = 0; i < categoriesNames.length; i++){
				if (exp.getCategory().equals(categoriesNames[i])) {
					if (includeCategory[i])
						resultList.add(exp);
				}
			}
		}
		return resultList;
	}

	static public List<Expense> last5(){
		List<Expense> allExpenses = Expenses.getAllExpenses();
		if (allExpenses.size() <= 5)
			return allExpenses;
		else
			return allExpenses.subList(allExpenses.size() - 5, allExpenses.size());
	}
}
