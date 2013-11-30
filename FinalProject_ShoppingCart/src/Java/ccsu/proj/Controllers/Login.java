/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import ccsu.proj.Model.Account;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Samuel Cawley
 */

@ManagedBean
@SessionScoped
public class Login implements Serializable {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @ManagedProperty(value = "#{account}")
    private Account account;
    
    public boolean usernameExists() {
        List<Account> accounts = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select a from Account a where a.username = :username"; 
        
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            selectQuery.setParameter("username", account.getUsername());
            accounts = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(accounts.size() == 1)
            return true;
        else
            return false;
    }
    
    public String validateAccount() {
        List<Account> accounts = new ArrayList();
        if(usernameExists()) {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            String selectSQL =
                    "select a from Account a where a.username = :username and a.pw_hash = :password_hash";
            
            try {
                Query selectQuery = entityManager.createQuery(selectSQL);
                selectQuery.setParameter("username", account.getUsername());
                selectQuery.setParameter("password_hash", account.getPW_Hash());
                accounts = selectQuery.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(accounts.size() == 1) {
                account = accounts.get(0);
                return "index";
            } else return "invalidPass";
        } else return "index?faces-redirect=true&v=register";
    }
    
    public void logout() {
        /* Invalidates the session and the account bean stored in it */
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Account getAccount() {
        return this.account;
    }
}
