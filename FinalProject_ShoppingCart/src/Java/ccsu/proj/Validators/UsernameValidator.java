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
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        /* Validate username. Makes sure name isn't already taken. Might be able to just use Login bean */
    }
    
}
