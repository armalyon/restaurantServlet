package ua.restaurant.srvlt.model.service;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exceptions.RegexMismatchException;
import ua.restaurant.srvlt.model.dto.AccountDTO;
import ua.restaurant.srvlt.model.service.utility.ValidationUtility;

public class RegFormValidationService {
    private static final Logger LOGGER = Logger.getLogger(RegFormValidationService.class);

    private ValidationUtility validationUtility = new ValidationUtility();


    public boolean isRegFormValid(AccountDTO accountDTO)
            throws ConfirmationDoesNotMatchException, RegexMismatchException {
        LOGGER.debug(accountDTO);
        return validationUtility
                .isPasswordCanBeUsed(accountDTO.getPassword(), accountDTO.getPasswordConfirmation())
                && validationUtility
                .isUsernameValid(accountDTO.getUsername())
                && validationUtility
                .areNameAndSurnameValid(accountDTO.getName(), accountDTO.getSurname());
    }
}
