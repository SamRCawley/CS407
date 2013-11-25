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
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Jason
 */

@ManagedBean()
public class ShoppingCart {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;  
    @ManagedProperty(value = "#{products}")
    private Products product;
    
    public List getShoppingCartItems() {
        List<Products> products = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        //Get cart items from context
        return products;
    }
    
    public void addItemToCart(Products product) {
        //Add a products to the cart. If it already exists,
        //increment its quantity
    }
    
    public void removeItemFromCart(Products product) {
        //Remove a product from the cart
    }
    
    public void changeProductQuantity(Products product, int quantity) {
        //Change the quantity of an individual item
    }
    
    public void clearShoppingCart() {
        //Clear the cart
    }
    
    public Products getProduct() {
        return product;
    }

   
    public void setProduct(Products product) {
        this.product = product;
    }
}