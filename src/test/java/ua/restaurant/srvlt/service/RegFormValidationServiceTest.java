package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.exception.ConfirmationDoesNotMatchException;
import ua.restaurant.srvlt.exception.RegexMismatchException;
import ua.restaurant.srvlt.service.utility.ValidationUtilityService;

import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class RegFormValidationServiceTest {

    private static final String NAME_ENG = "Name";
    private static final String SURNAME_ENG = "Surname";
    private static final String USERNAME = "USERNAME";
    private static final String PASSWORD = "PASSWORD";

    private static final AccountDTO ACCOUNT_DTO_ENG = new AccountDTO.Builder()
            .name(NAME_ENG)
            .surname(SURNAME_ENG)
            .username(USERNAME)
            .password(PASSWORD)
            .passwordConfirmation(PASSWORD)
            .build();

    @InjectMocks
    private RegFormValidationService instance;

    @Mock
    private ValidationUtilityService validationUtilityService;

    @Before
    public void setUp() throws ConfirmationDoesNotMatchException, RegexMismatchException {
        when(validationUtilityService
                .isPasswordCanBeUsed(ACCOUNT_DTO_ENG.getPassword(),
                        ACCOUNT_DTO_ENG.getPasswordConfirmation())).thenReturn(true);
        when(validationUtilityService
                .isUsernameValid(ACCOUNT_DTO_ENG.getUsername())).thenReturn(true);
        when(validationUtilityService
                .validateNameAndSurname(ACCOUNT_DTO_ENG.getName(),
                        ACCOUNT_DTO_ENG.getSurname())).thenReturn(true);
    }

    @Test
    public void shouldReturnTrueWhenFormIsValid() throws ConfirmationDoesNotMatchException, RegexMismatchException {
        boolean result = instance.isRegFormValid(ACCOUNT_DTO_ENG);
        Assert.assertTrue(result);
    }


}