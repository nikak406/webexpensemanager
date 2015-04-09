package webexpensemanager.servlet;

import webexpensemanager.logic.Categories;
import webexpensemanager.ejb.Dates;
import webexpensemanager.ejb.HTMLWrapper;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CategoriesServlet extends HttpServlet{

	@EJB
	HTMLWrapper htmlWrapper;

	@EJB
	Dates dates;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String[] categories = Categories.getCategoriesArray();
		String categoriesList = htmlWrapper.wrapTable(categories);
		String selectCategories = htmlWrapper.wrapSelect(categories, "category");
		request.setAttribute("categoriesSelect", selectCategories);
		request.setAttribute("categoriesList", categoriesList);
		request.getRequestDispatcher("categories.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String categoryAdd = request.getParameter("newCategory");
		if (categoryAdd != null)
			Categories.findCategory(categoryAdd);
		String categoryRemove=request.getParameter("category");
		if (categoryRemove != null){
			try {
				Categories.removeCategory(categoryRemove);
			} catch (EJBException e) {
				request.setAttribute("errorMessage", "Cannot remove " + categoryRemove
						+ ": there are expenses in this category.");
			}
		}
		doGet(request, response);
	}
}
