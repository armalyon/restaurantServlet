package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.exceptions.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exceptions.RegexMismatchException;
import ua.restaurant.srvlt.model.service.utility.ValidationUtility;

public class RegFormValidationService {

    private ValidationUtility validationUtility = new ValidationUtility();


    public boolean isRegFormValid(AccountDTO accountDTO)
            throws ConfirmationDoesNotMatchException, RegexMismatchException {
        return validationUtility
                .isPasswordCanBeUsed(accountDTO.getPassword(), accountDTO.getPasswordConfirmation())
                && validationUtility
                .isUsernameValid(accountDTO.getUsername())
                && validationUtility
                .areNameAndSurnameValid(accountDTO.getName(), accountDTO.getSurname());
    }
}
