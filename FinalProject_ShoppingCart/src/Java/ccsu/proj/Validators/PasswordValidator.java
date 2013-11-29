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
@FacesValidator(value="passwordValidator")
public class PasswordValidator implements Validator {
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        /* Validate password. Make sure confirm password and password match */
    }
}
