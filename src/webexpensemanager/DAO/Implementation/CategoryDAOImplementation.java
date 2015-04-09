package webexpensemanager.DAO.Implementation;

import webexpensemanager.DAO.CategoryDAO;
import webexpensemanager.logic.Category;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Singleton
public class CategoryDAOImplementation implements CategoryDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public void addCategory(Category category) throws SQLException {
			em.persist(category);
	}

	@Override
	public Set<Category> getAllCategory() throws SQLException {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Category> cq = cb.createQuery(Category.class);
		Root<Category> rootEntry = cq.from(Category.class);
		CriteriaQuery<Category> all = cq.select(rootEntry);
		TypedQuery<Category> allQuery = em.createQuery(all);
		List<Category> list = allQuery.getResultList();
		return new HashSet<>(list);
	}

	@Override
	public void deleteCategory(Category category) throws SQLException {
			category = em.find(Category.class, category.getName());
			em.remove(category);
	}
}
