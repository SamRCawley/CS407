/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputText;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

/**
 *
 * @author Jason
 */
@FacesValidator(value="productNameValidator")
public class ProductNameValidator implements Validator {
    @PersistenceUnit(unitName="FinalProject_ShoppingCartPU")
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        /* Validate product. Makes sure name isn't already taken. */
        String product = value.toString();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        String selectSQL = "select p from Products p where p.productName = :product";
        Query selectQuery = entityManager.createQuery(selectSQL);
        selectQuery.setParameter("product", product);
        
        if (selectQuery.getResultList().size() > 0) {
            FacesMessage facesMessage =  new FacesMessage("Product already exists");
            throw new ValidatorException(facesMessage);
        }
    }
}
