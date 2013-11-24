/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import ccsu.proj.Classes.SHA1MessageDigest;
import ccsu.proj.Model.Account;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
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

@ManagedBean
@SessionScoped
public class Login {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;  //use this later for creating new accounts
    @ManagedProperty(value = "#{account}")
    private Account account;
    
    public boolean usernameExists()
    {
        List<Account> accounts = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select a from Account a where a.username = :username"; 
        //Something with passwords hashing generation
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
        if(!usernameExists())
            return "noSuchUsername";
        else {
            EntityManager entityManager = entityManagerFactory.createEntityManager();
            String selectSQL = "select a from Account a where a.username = :username and a.pw_hash = :password_hash";
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            String pw = ec.getRequestParameterMap().get("password");
            SHA1MessageDigest sha1 = new SHA1MessageDigest();
            String pw_hash = sha1.digestMessage(pw);
            /* Maybe? */
            
            try {
                Query selectQuery = entityManager.createQuery(selectSQL);
                selectQuery.setParameter("username", account.getUsername());
                selectQuery.setParameter("password_hash", pw_hash); //hashed password
                accounts = selectQuery.getResultList();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(accounts.size() == 1) {
                account = accounts.get(0);
                return "authenticated";
            }
            else
                return "invalidPass";
        }
    }
    
    public void setAccount(Account account) {
        this.account = account;
    }
    
    public Account getAccount() {
        return this.account;
    }
}
