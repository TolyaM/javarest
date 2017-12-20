package rnd.auth.validator;


import org.apache.tomcat.jdbc.pool.Validator;
import org.springframework.validation.Errors;
import rnd.auth.model.User;

import java.sql.Connection;

/*
public class UserValidator implements Validator {

    @Override
    public void validate(Object obj, Errors errors) {

        User user = (User) obj;
        if (checkInputString(user.getLast_naame())) {
            errors.rejectValue("name", "name.empty");
        }

        if (checkInputString(user.getEmail())) {
            errors.rejectValue("email", "email.empty");
        }
    }

    private boolean checkInputString(String input) {
        return (input == null || input.trim().length() == 0);
    }
}
*/
