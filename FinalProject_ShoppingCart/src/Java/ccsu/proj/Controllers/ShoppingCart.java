/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;


import ccsu.proj.Model.Orders;
import ccsu.proj.Model.Products;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;

/**
 *
 * @author Jason
 */

@ManagedBean
@SessionScoped
public class ShoppingCart implements Serializable{
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;  
    private Set<Products> cart = new HashSet();
    
    public Set getShoppingCartItems() {
        return cart;
    }
    
    public void addItemToCart(Products product) {
        cart.add(product);
    }
    
    public void removeItemFromCart(Products product) {
        cart.remove(product);
    }
    
    public void clearShoppingCart() {
        cart.clear();
    }
    
    public String finalizeOrder(){
        Orders order= new Orders();
        java.util.Date utilDate = new Date();
        java.sql.Date date = new java.sql.Date(utilDate.getTime());
        order.setDate(date);
        order.setProducts(cart);
        String saved = "error";
        try {
            userTransaction.begin();
            EntityManager em = entityManagerFactory.createEntityManager();
            em.persist(order);
            userTransaction.commit();
            em.close();
            saved = "confirmation";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return saved;
    }
}