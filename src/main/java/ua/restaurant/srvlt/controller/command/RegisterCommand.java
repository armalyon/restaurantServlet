package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.exceptions.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exceptions.RegexMismatchException;
import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.dto.AccountDTO;
import ua.restaurant.srvlt.model.service.RegFormValidationService;
import ua.restaurant.srvlt.model.service.UserRegistrationService;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;

import static ua.restaurant.srvlt.constants.StringConstants.*;
import static ua.restaurant.srvlt.model.service.utility.EncodingUtility.getUTF8String;

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
                .username(getUTF8String(
                        request.getParameter(USERNAME_ATTRIBUTE),
                        StandardCharsets.ISO_8859_1))
                .name(getUTF8String(
                        request.getParameter(NAME_ATTRIBUTE),
                        StandardCharsets.ISO_8859_1))
                .surname(getUTF8String(
                        request.getParameter(SURNAME_ATTRIBUTE),
                        StandardCharsets.ISO_8859_1))
                .password(getUTF8String(request.getParameter(PASSWORD_ATTRIBUTE),
                        StandardCharsets.ISO_8859_1))
                .passwordConfirmation(getUTF8String(
                        request.getParameter(PASSWORD_CONFIRMATION_ATTRIBUTE),
                        StandardCharsets.ISO_8859_1))
                .build();
    }


}
