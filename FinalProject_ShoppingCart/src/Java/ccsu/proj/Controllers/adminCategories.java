package ccsu.proj.Controllers;

import ccsu.proj.Model.Categories;
import java.util.Collection;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Jonathan Montwell
 */
@ManagedBean()
public class adminCategories {

    @PersistenceUnit(unitName = "FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    @ManagedProperty(value = "#{categories}")
    private Categories categories;
    private Collection<Categories> collection;
    private int searchNumber;
    private String categoryname;

    public void setSearchNumber(int searchNumber) {
        this.searchNumber = searchNumber;
        searchByNumber();
    }

    public int getSearchNumber() {
        return searchNumber;
    }

    public void searchByNumber() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Categories.byID");
        query.setParameter("number", searchNumber);
        collection = query.getResultList();
    }

    public void setSearchCategoryName(String categoryname) {
        this.categoryname = categoryname + "%";
        searchByCategoryName();
    }

    public String getSearchCategoryName() {
        return categoryname;
    }

    public void searchByCategoryName() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Categories.byName");
        query.setParameter("categoryname", categoryname);
        collection = query.getResultList();
    }

    public Collection<Categories> getCollection() {
        return collection;
    }

    public List<Categories> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Categories.findAll");
        List<Categories> all = query.getResultList();
        return all;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public String createCategory() {
        String returnValue = "error_saving_category";
        try {
            incrementCategoryID();
            userTransaction.begin();
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.persist(categories);
            userTransaction.commit();
            entityManager.close();
            returnValue = "index";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    private void incrementCategoryID() {
        int newID = 0;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select max(c.cid) from Categories c"; 
        
        try {
            newID = (Integer)entityManager.createQuery(selectSQL).getSingleResult() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        categories.setCID(newID);
    }
}