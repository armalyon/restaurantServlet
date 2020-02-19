package ua.restaurant.srvlt.model.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

import java.time.LocalDateTime;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationServiceTest {
    private final static String USERNAME = "username";
    private final static String USERNAME_REGISTERED = "username2";
    private final static String PASSWORD = "password";
    private final static String NAME = "Name";
    private final static String SURNAME = "Surname";
    private final static AccountDTO validAccountDTO = new AccountDTO.Builder()
            .name(NAME)
            .surname(SURNAME)
            .username(USERNAME)
            .password(PASSWORD)
            .passwordConfirmation(PASSWORD)
            .build();
    private final static AccountDTO invalidAccountDTO = new AccountDTO.Builder()
            .name(NAME)
            .surname(SURNAME)
            .username(USERNAME_REGISTERED)
            .password(PASSWORD)
            .passwordConfirmation(PASSWORD)
            .build();

    @InjectMocks
    private UserRegistrationService instance;

    @Mock
    private UserDao userDao;
    @Mock
    private User user;;

    @Before
    public void setUp() throws UserExistsException {
        when(user.getFunds()).thenReturn(25L);
        when(user.getUsername()).thenReturn(USERNAME_REGISTERED);
        when(user.getPassword()).thenReturn(PASSWORD);
        when(user.getName()).thenReturn(NAME);
        when(user.getRole()).thenReturn(Role.CLIENT);
        when(user.getRegistrationDate()).thenReturn(LocalDateTime.now());
        when(user.getId()).thenReturn(0L);
        when(user.getSurname()).thenReturn(SURNAME);
        doThrow(UserExistsException.class).when(userDao).createNewUser(user);
    }


    @Test(expected = Test.None.class)
    public void shouldNotThrowExceptionWhenUsernameNotRegistered() throws UserExistsException {
        instance.saveNewUser(validAccountDTO);
    }

    @Test(expected = UserExistsException.class)
    public void shouldThrowExceptionWhenUrernameRegistered() throws UserExistsException {
        instance.saveNewUser(invalidAccountDTO);

    }

}
