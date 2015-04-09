package webexpensemanager.DAO.Implementation;

import webexpensemanager.DAO.ExpenseDAO;
import webexpensemanager.logic.Expense;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.List;

@Singleton
public class ExpenseDAOImplementation implements ExpenseDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public void addExpense(Expense expense) throws SQLException {
		em.persist(expense);
	}

	@Override
	public List<Expense> getAllExpense() throws SQLException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Expense> cq = cb.createQuery(Expense.class);
		Root<Expense> rootEntry = cq.from(Expense.class);
		CriteriaQuery<Expense> all = cq.select(rootEntry);
		TypedQuery<Expense> allQuery = em.createQuery(all);
		return allQuery.getResultList();

	}

	@Override
	public Expense getExpenseById(Integer id) throws SQLException {
		return em.find(Expense.class, id);
	}

	@Override
	public void deleteExpense(Expense expense) throws SQLException {
		expense = em.find(Expense.class, expense.getNumber());
		em.remove(expense);
	}
}
