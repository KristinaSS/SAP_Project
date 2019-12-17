/*
package sapproject.project.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import sapproject.project.models.Account;
import sapproject.project.services.interfaces.IAccountService;

@Component
public class UserValidator implements Validator {
    @Autowired
    private IAccountService userService;
    @Override
    public boolean supports(Class aClass) {
        return Account.class.equals(aClass);
    }
    @Override
    public void validate(Object o, Errors errors) {
        Account user = (Account) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "NotEmpty");
        if (user.getEmail().length() < 6 || user.getEmail().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }
    */
/*    if (userService.findByUsername(user.getEmail()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }*//*

    */
/*if (!user.getPasswordConfirm().equals(user.getPassword())) {
            errors.rejectValue("passwordConfirm", "Diff.userForm.passwordConfirm");
        }*//*

    }
}
*/
