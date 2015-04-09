package webexpensemanager.DAO;

import javax.ejb.EJB;
import javax.ejb.Singleton;

@Singleton
public class Factory {

	@EJB
	private CategoryDAO categoryDAO;
	@EJB
	private ExpenseDAO expenseDAO;

	private static Factory instance = null;

	public static void setInstance(Factory factory){
		instance = factory;
	}

	public static synchronized Factory getInstance(){
		return instance;
	}

	public CategoryDAO getCategoryDAO(){
		return categoryDAO;
	}

	public ExpenseDAO getExpenseDAO(){
		return expenseDAO;
	}
}
