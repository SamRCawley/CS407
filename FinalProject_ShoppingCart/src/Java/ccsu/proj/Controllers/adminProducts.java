package ccsu.proj.Controllers;

import ccsu.proj.Model.Categories;
import ccsu.proj.Model.Products;
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
public class adminProducts {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    private Products product;   
    private List<Products> collection;
    private int searchNumber;
    private String productname;
    private int selectedCategory;
    private int selectedProduct;
    
    public void setSelectedCategory(int selectedCategory) {
        this.selectedCategory = selectedCategory;
    }
    public int getSelectedCategory() {
        return selectedCategory;
    }
    public void setSelectedProduct(int selectedProduct) {
        this.selectedProduct = selectedProduct;
    }
    public int getSelectedProduct() {
        return selectedProduct;
    }
    public void setSearchNumber(int searchNumber) {
        this.searchNumber = searchNumber;
        searchByNumber();
    }
    public int getSearchNumber() {
        return searchNumber;
    }
    public void searchByNumber() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Products.byID");        
        query.setParameter("number", searchNumber);
        collection = query.getResultList();
    }
    public void setSearchProductName(String productname) {
        this.productname = productname + "%";
        searchByProductName();
    }
    public String getSearchProductName(){
        return productname;
    }
    public void searchByProductName() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Products.byName");        
        query.setParameter("productname", productname);
        collection = query.getResultList();
    }
    public Collection<Products> getCollection() {
        return collection;
    }
    public List<Products> getAll() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Products.findAll");
        List<Products> all = query.getResultList();        
        return all;
    }
    public Products getProduct() {
        return product;
    }

   
    public void setProduct(Products product) {
        this.product = product;
    }
    public String createProduct(){
        String returnValue = "error_saving_product";
        try {
            incrementProductID();
            userTransaction.begin();        
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            entityManager.persist(product);
            userTransaction.commit();
            entityManager.close();
            returnValue = "product_saved";
        } catch(Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    private void incrementProductID() {
        int newID = 0;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select max(p.id) from Products p"; 
        
        try {
            newID = (Integer)entityManager.createQuery(selectSQL).getSingleResult() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        product.setID(newID);
    }
    
    // Not Persisting not sure why... 
    public String addToCategory(){
        String returnValue = "add to Category Failed";
        EntityManager entityManager1 = entityManagerFactory.createEntityManager();
        Query query = entityManager1.createNamedQuery("Products.byID");        
        query.setParameter("number", selectedProduct);
        List<Products> pResults = query.getResultList();
        if(!pResults.isEmpty()){
            Products productRetrieved = pResults.iterator().next();
            List<Categories> currentCategories = productRetrieved.getCategories();
            EntityManager entityManager2 = entityManagerFactory.createEntityManager();
            query = entityManager2.createNamedQuery("Categories.byID");        
            query.setParameter("number", selectedCategory);
            List<Categories> cResults = query.getResultList();
            entityManager2.close();
            if(!cResults.isEmpty()){
                Categories category = cResults.iterator().next();
                currentCategories.add(category);
                productRetrieved.setCategories(currentCategories);
                try {
                    userTransaction.begin();
                    entityManager1.merge(productRetrieved);
                    userTransaction.commit();
                    entityManager1.close();
                    returnValue = "added to category";
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("theend");
        return returnValue;
    }
}