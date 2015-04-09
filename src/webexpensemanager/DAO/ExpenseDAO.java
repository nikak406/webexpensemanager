package webexpensemanager.DAO;

import webexpensemanager.logic.Expense;

import java.sql.SQLException;
import java.util.List;

public interface ExpenseDAO {
	public void addExpense(Expense expense) throws SQLException;
	public List<Expense> getAllExpense() throws SQLException;
	public Expense getExpenseById(Integer id) throws SQLException;
	public void deleteExpense(Expense expense) throws SQLException;
}
