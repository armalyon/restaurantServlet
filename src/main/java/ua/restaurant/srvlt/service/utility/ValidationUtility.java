package ua.restaurant.srvlt.service.utility;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exception.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exception.RegexMismatchException;

import java.util.Objects;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

import static ua.restaurant.srvlt.constant.StringConstants.*;

public class ValidationUtility {
    private final static String USERNAME_REGEX = "regex.validation.username";
    private final static String NAME_REGEX = "regex.validation.name";
    private final static String PASSWORD_REGEX = "regex.validation.password";

    private ResourceBundle bundle = ResourceBundle.getBundle("regexes");

    public boolean isPasswordCanBeUsed(String password,
                                       String confirmation
    ) throws ConfirmationDoesNotMatchException, RegexMismatchException {
        if (!password.equals(confirmation))
            throw new ConfirmationDoesNotMatchException("Password and confirmation do not match");

        if (!getPattern(PASSWORD_REGEX).matcher(password).matches()) {
            throw new RegexMismatchException("RegEx mismatch", PASSWORD_FIELD);
        }
        return true;
    }

    public boolean isUsernameValid(String username) throws RegexMismatchException {
        if (!getPattern(USERNAME_REGEX).matcher(username).matches()) {
            throw new RegexMismatchException("RegEx mismatch", USERNAME_FIELD);
        }
        return true;
    }

    public boolean validateNameAndSurname(String name, String surname) throws RegexMismatchException {
        Pattern pattern = getPattern(NAME_REGEX);
        if (!pattern.matcher(name).matches() ||
                !pattern.matcher(surname).matches()) throw new RegexMismatchException("RegEx mismatch", NAME_FIELD);
        return true;
    }

    private Pattern getPattern(String regexContainer) {
        return Pattern.compile(Objects.requireNonNull(
                bundle.getString(regexContainer))
        );
    }
}
