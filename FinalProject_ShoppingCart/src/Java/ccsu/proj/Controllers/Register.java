/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import ccsu.proj.Model.Account;
import java.io.Serializable;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
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
public class Register implements Serializable {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    @ManagedProperty(value = "#{account}")
    private Account account;
    
    public Register() {
    
    }
    
    public String registerNewUser() {
        String returnValue = "registrationError";
        try {
            incrementAccountID(); //Make sure account id is not zero
            userTransaction.begin();
            EntityManager em = entityManagerFactory.createEntityManager();
            em.persist(account);
            userTransaction.commit();
            em.close();
            returnValue = "registrationSuccess";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }
    
    private void incrementAccountID() {
        int newID = 0;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select max(id) from Account"; 
        
        try {
            newID = (Integer)entityManager.createQuery("select max(a.id) from Account a").getSingleResult() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        account.setId(newID);
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Account getAccount() {
        return this.account;
    }
}


