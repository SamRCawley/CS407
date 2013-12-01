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

/**
 *
 * @author Jason
 */
@FacesValidator(value="productPriceValidator")
public class ProductPriceValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        float price = Float.parseFloat(value.toString());
        
        if (price < 0.0f) {
            FacesMessage facesMessage =  new FacesMessage("Please enter a valid price");
            throw new ValidatorException(facesMessage);
        }
    }
}
