/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import ccsu.proj.Model.Orders;
import java.util.Collection;
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
public class adminOrders {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @ManagedProperty(value = "#{orders}")
    private Orders order;
    /**
     * Creates a new instance of adminOrders
     */
    public Collection<Orders> getByDateAsc() {
        Collection<Orders> collection;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Orders.sortDateAsc");
        collection = query.getResultList();
        System.out.println("excuted get date");
        return collection;        
    }
    public Collection<Orders> getByDateDesc() {
        Collection<Orders> collection;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Query query = entityManager.createNamedQuery("Orders.sortDateDesc");
        collection = query.getResultList();
        return collection;
    }
    public Orders getOrder() {
        return order;
    }

   
    public void setOrder(Orders order) {
        this.order = order;
    }
}
