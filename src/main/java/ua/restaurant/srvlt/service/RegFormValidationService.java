package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.exception.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exception.RegexMismatchException;
import ua.restaurant.srvlt.service.utility.ValidationUtility;

public class RegFormValidationService {

    private ValidationUtility validationUtility = new ValidationUtility();

    public boolean isRegFormValid(AccountDTO accountDTO)
            throws ConfirmationDoesNotMatchException, RegexMismatchException {
        return validationUtility
                .isPasswordCanBeUsed(accountDTO.getPassword(), accountDTO.getPasswordConfirmation())
                && validationUtility
                .isUsernameValid(accountDTO.getUsername())
                && validationUtility
                .validateNameAndSurname(accountDTO.getName(), accountDTO.getSurname());
    }
}
