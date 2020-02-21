package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.exception.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exception.RegexMismatchException;
import ua.restaurant.srvlt.service.utility.ValidationUtilityService;

public class RegFormValidationService {

    private ValidationUtilityService validationUtilityService = new ValidationUtilityService();

    public boolean isRegFormValid(AccountDTO accountDTO)
            throws ConfirmationDoesNotMatchException, RegexMismatchException {
        return validationUtilityService
                .isPasswordCanBeUsed(accountDTO.getPassword(), accountDTO.getPasswordConfirmation())
                && validationUtilityService
                .isUsernameValid(accountDTO.getUsername())
                && validationUtilityService
                .validateNameAndSurname(accountDTO.getName(), accountDTO.getSurname());
    }
}
