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
import java.util.Map;
import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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
@SessionScoped
public class adminAccounts implements Serializable {

    @PersistenceUnit(unitName = "FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;
    @Resource
    private UserTransaction userTransaction;
    @ManagedProperty(value = "#{s_account}")
    private Account account;
    FacesContext fc = FacesContext.getCurrentInstance();

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
        String returnValue = "registrationError";
        try {
            incrementAccountID(); //Make sure account id is not zero
            userTransaction.begin();
            EntityManager em = entityManagerFactory.createEntityManager();
            em.persist(account);
            userTransaction.commit();
            em.close();
            returnValue = "index?faces-redirect=true&v=manage_accounts";
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

    public String removeAccount() {
        String returnValue = "error";
        try {
            userTransaction.begin();
            EntityManager em = entityManagerFactory.createEntityManager();
            Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
            int id = Integer.parseInt(params.get("id"));
            account = em.find(Account.class, id);
            em.remove(account);
            userTransaction.commit();
            em.close();
            returnValue = "success";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return this.account;
    }
}
