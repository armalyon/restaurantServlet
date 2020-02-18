package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.exceptions.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exceptions.RegexMismatchException;
import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.dto.AccountDTO;
import ua.restaurant.srvlt.model.service.RegFormValidationService;
import ua.restaurant.srvlt.model.service.UserRegistrationService;
import javax.servlet.http.HttpServletRequest;
import static ua.restaurant.srvlt.constants.StringConstants.*;

public class RegisterCommand implements Command {
    private RegFormValidationService regFormValidationService = new RegFormValidationService();
    private UserRegistrationService userRegistrationService = new UserRegistrationService();

    @Override
    public String execute(HttpServletRequest request) {

        AccountDTO accountDTO = getAccountDTO(request);

        try {
            regFormValidationService.isRegFormValid(accountDTO);
            userRegistrationService.saveNewUser(accountDTO);
        } catch (ConfirmationDoesNotMatchException e) {
            request.setAttribute(ERROR, PASSWORD_CONFIRMATION_ATTRIBUTE);
            return REGISTRATION_PAGE;
        } catch (RegexMismatchException e) {
            request.setAttribute(ERROR, e.getField());
            return REGISTRATION_PAGE;
        } catch (UserExistsException e) {
            request.setAttribute(ERROR, LOGIN);
            return REGISTRATION_PAGE;
        }
        return LOGIN_PAGE;
    }


    private AccountDTO getAccountDTO(HttpServletRequest request) {
        return new AccountDTO.Builder()
                .username(request.getParameter(USERNAME_ATTRIBUTE))
                .name(request.getParameter(NAME_ATTRIBUTE))
                .surname( request.getParameter(SURNAME_ATTRIBUTE))
                .password(request.getParameter(PASSWORD_ATTRIBUTE))
                .passwordConfirmation(request.getParameter(PASSWORD_CONFIRMATION_ATTRIBUTE))
                .build();
    }

}
