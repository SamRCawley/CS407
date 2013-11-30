/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Validators;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Jason
 */

@FacesValidator(value="usernameValidator")
public class UsernameValidator implements Validator {
    //private EntityManagerFactory entityManagerFactory;
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        /* Validate username. Makes sure name isn't already taken. Might be able to just use Login bean */
        String username = value.toString();
        
//        if (usernameExists(username)) {
//            FacesMessage facesMessage =  new FacesMessage("Username is already taken");
//            throw new ValidatorException(facesMessage);
//        }
    }
    
//    public boolean usernameExists(String username) {
//        List<Account> accounts = new ArrayList();
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        String selectSQL = "select a from Account a where a.username = :username"; 
//        
//        try {
//            Query selectQuery = entityManager.createQuery(selectSQL);
//            selectQuery.setParameter("username", username);
//            accounts = selectQuery.getResultList();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        if(accounts.size() == 1)
//            return true;
//        else
//            return false;
//    }
}
