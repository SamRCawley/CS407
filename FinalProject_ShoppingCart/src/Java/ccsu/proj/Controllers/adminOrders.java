/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import ccsu.proj.Model.Orders;
import java.util.Collection;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Jonathan Montwell
 */
@ManagedBean()
public class adminOrders {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @ManagedProperty(value = "#{orders}")
    private Orders order;    
    private Collection<Orders> collection;
    private int searchNumber;
    private String username;
    
    /**
     * Creates a new instance of adminOrders
     */
    
    public void setSearchNumber(int searchNumber) {
        this.searchNumber = searchNumber;
        searchByNumber();
    }
    
    public int getSearchNumber() {
        return searchNumber;
    }
    
    public void searchByNumber() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Orders.byOrder");        
        query.setParameter("number", searchNumber);
        collection = query.getResultList();
    }
    
    public void setSearchUsername(String username) {
        this.username = username + "%";
        searchByUsername();
    }
    
    public String getSearchUsername() {
        return username;
    }
    
    public void searchByUsername() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Orders.byUsername");        
        query.setParameter("username", username);
        collection = query.getResultList();
    }
    
    public void getByDateAsc() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Orders.sortDateAsc");
        collection = query.getResultList();
    }
    
    public void getByDateDesc() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Orders.sortDateDesc");
        collection = query.getResultList();
    }
    
    public Collection<Orders> getCollection() {
        return collection;
    }
    
    public Orders getOrder() {
        return order;
    }
    
    public void setOrder(Orders order) {
        this.order = order;
    }
}
