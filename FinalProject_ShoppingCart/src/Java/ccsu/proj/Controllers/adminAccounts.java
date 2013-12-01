/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Controllers;

import ccsu.proj.Model.Account;
import ccsu.proj.Model.Products;
import java.io.Serializable;
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
 * @author Jason
 */
@ManagedBean
public class adminAccounts implements Serializable {

    @PersistenceUnit(unitName = "FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    @ManagedProperty(value = "#{account}")
    private Account account;

    public adminAccounts() {
    }

    public List getAccounts() {
        List<Products> accounts = new ArrayList();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select a from Account a";
        try {
            Query selectQuery = entityManager.createQuery(selectSQL);
            accounts = selectQuery.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return accounts;
    }

    public String registerNewUser() {
        String returnValue = "error";
        try {
            incrementAccountID(); //Make sure account id is not zero
            userTransaction.begin();
            EntityManager em = entityManagerFactory.createEntityManager();
            em.persist(account);
            userTransaction.commit();
            em.close();
            returnValue = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    private void incrementAccountID() {
        int newID = 0;
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select max(a.id) from Account a";

        try {
            newID = (Integer) entityManager.createQuery(selectSQL).getSingleResult() + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }

        account.setID(newID);
    }

    public String editAccount() {
        String returnValue = "error";

        return returnValue;
    }

    //Does not work
    public String removeAccount() {
        String returnValue = "error";
//        try {
//            userTransaction.begin();
//            EntityManager em = entityManagerFactory.createEntityManager();
//            FacesContext context = FacesContext.getCurrentInstance();
//            Map<String, String> requestMap = context.getExternalContext().getRequestParameterMap();
//            account = em.find(Account.class, requestMap.get("id"));
//            em.remove(account);
//            userTransaction.commit();
//            em.close();
//            returnValue = "success";
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return returnValue;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }
}