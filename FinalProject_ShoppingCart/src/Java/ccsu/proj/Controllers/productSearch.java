/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;


import ccsu.proj.Model.Categories;
import ccsu.proj.Model.Products;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.transaction.UserTransaction;

/**
 *
 * @author Samuel Cawley
 */

@ManagedBean()
public class productSearch {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;  
    @ManagedProperty(value = "#{products}")
    private Products product;
    
    FacesContext fc = FacesContext.getCurrentInstance();
    
    public List getMatchingProducts(String category) {
        List<Products> products = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        if(category == null)
            category = "";
        String selectSQL = "select p from Products p where p.productName like :name AND EXISTS(SELECT c.categoryName  FROM p.categories c WHERE c.categoryName like :category)"; 
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            selectQuery.setParameter("name", product.getProductName() + "%");
            selectQuery.setParameter("category", category + "%");
            products = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    public List getProductCategories() {
        List<Categories> categories = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select c from Categories c"; 
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            categories = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categories;
    }
    
    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }
    
}
