package webexpensemanager.logic;

import webexpensemanager.DAO.CategoryDAO;
import webexpensemanager.DAO.Factory;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class Categories {

	static private Set<Category> setOfCategories = new HashSet<>();

	static public void readCategoriesFromDB(String addName, String removeName){
		try {
			if (!removeName.equals(""))
				Factory.getInstance().getCategoryDAO().deleteCategory(findCategory(removeName));
			if (!addName.equals(""))
				Factory.getInstance().getCategoryDAO().addCategory(new Category(addName));
			Factory factory = Factory.getInstance();
			CategoryDAO categoryDAO = factory.getCategoryDAO();
			setOfCategories = categoryDAO.getAllCategory();
		}
		catch (SQLException e) {
			System.err.println("SQL Exception (Categories): " + e);
		}
	}

	static public String[] getCategoriesArray(){
		String[] res = new String[setOfCategories.size()];
		int i = 0;
		for (Category c : setOfCategories){
			res[i++] = c.getName();
		}
		return res;
	}

	static public Category findCategory(String name){
		if (name == null) return null;
		for (Category c : setOfCategories){
			if (c.getName().equals(name)) return c;
		}
		return addCategory(name);
	}

	static public void removeCategory(String name){
		Category remove=null;
		for (Category c : setOfCategories) {
			if (c.getName().equals(name)) remove = c;
		}
		if (remove != null) readCategoriesFromDB("", remove.getName());
	}

	static private Category addCategory(String name){
		readCategoriesFromDB(name, "");
		for(Category c : setOfCategories){
			if (c.getName().equals(name)) return c;
		}
		return null;
	}

	static public boolean[] allTrueCategory(){
		int k = Categories.getCategoriesArray().length;
		boolean res[] = new boolean[k];
		for (int i = 0; i < k; i++){
			res[i] = true;
		}
		return res;
	}
}
