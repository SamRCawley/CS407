/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;


import ccsu.proj.Model.Products;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
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
    
    public List getMatchingProducts()
    {
        List<Products> products = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select p from Products p where p.productName like :name"; 
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            selectQuery.setParameter("name", product.getProductName()+ "%");
            products = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
    
    public Products getProduct() {
        return product;
    }

   
    public void setProduct(Products product) {
        this.product = product;
    }
    
}
