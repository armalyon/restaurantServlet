package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mindrot.jbcrypt.BCrypt;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.Role;
import ua.restaurant.srvlt.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.Role.CLIENT;

@RunWith( MockitoJUnitRunner.class )
public class LoginServiceTest {

    private static final String NAME = "Name";
    private static final String SURNAME = "Surname";
    private static final String PASSWORD = "password";
    private static final String PASSWORD_INCORRECT = "password1";
    private static final String USERNAME = "username";
    private static final String USERNAME_NOT_FOUND = "username not found";
    private static final Role CLIENT_ROLE = CLIENT;
    private static final User USER = new User.Builder()
            .id(11L)
            .funds(25)
            .name(NAME)
            .surname(SURNAME)
            .password(BCrypt.hashpw(PASSWORD, BCrypt.gensalt()))
            .registrationDate(LocalDateTime.MIN)
            .username(USERNAME)
            .role(CLIENT_ROLE)
            .build();

    @InjectMocks
    private LoginService instance;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        when(userDao.findUserByUsername(USERNAME)).thenReturn(Optional.of(USER));
        doThrow(UserNotFoundException.class).when(userDao).findUserByUsername(USERNAME_NOT_FOUND);
    }

    @Test
    public void shouldReturnRoleIfUserExist() throws UserNotFoundException {
        Role result = instance.getUserRoleByUsername(USERNAME);
        Assert.assertEquals(CLIENT_ROLE, result);
    }

    @Test( expected = UserNotFoundException.class )
    public void shouldThrowExceptionIfUserNotFoundWhenGettingRole() throws UserNotFoundException {
        instance.getUserRoleByUsername(USERNAME_NOT_FOUND);
    }

    @Test
    public void shouldReturnTrueIfPwdCorrect() throws UserNotFoundException {
        boolean result = instance.isPasswordCorrect(USERNAME, PASSWORD);
        Assert.assertTrue(result);
    }

    @Test
    public void shouldReturnFalseWhenPasswordIncorrect() throws UserNotFoundException {
        boolean result = instance.isPasswordCorrect(USERNAME, PASSWORD_INCORRECT);
        Assert.assertFalse(result);
    }

    @Test( expected = UserNotFoundException.class )
    public void shouldThrowExceptionIfUserNotFoundWhenValidatingPwd() throws UserNotFoundException {
        instance.isPasswordCorrect(USERNAME_NOT_FOUND, PASSWORD);
    }

}