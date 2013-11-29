/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ccsu.proj.Validators;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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
        String password = value.toString();
 
	UIInput uiInputConfirmPassword = (UIInput) component.getAttributes().get("confirm");
	String confirm = uiInputConfirmPassword.getSubmittedValue().toString();

        if (password == null || password.isEmpty() || confirm == null || confirm.isEmpty()) {
            return; // Let required="true" do its job.
        }

        // Compare the password with the confirm password.
        if (!password.equals(confirm)) {
            uiInputConfirmPassword.setValid(false);
            FacesMessage facesMessage =  new FacesMessage("Password must match confirm password");
            throw new ValidatorException(facesMessage);
        }
    }
}
