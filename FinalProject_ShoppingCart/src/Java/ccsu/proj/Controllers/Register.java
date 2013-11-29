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
    //@ManagedProperty(value = "#{account}")
    private Account account;
    
    public Register() {
    
    }
    
    public void registerNewUser() {
        
    }
    
    //An empty controller at the moment
}


