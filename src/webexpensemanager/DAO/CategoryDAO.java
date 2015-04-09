package webexpensemanager.DAO;

import webexpensemanager.logic.Category;

import java.sql.SQLException;
import java.util.Set;

public interface CategoryDAO {
	public void addCategory(Category category) throws SQLException;
	public Set<Category> getAllCategory() throws SQLException;
	public void deleteCategory(Category category) throws SQLException;
}
