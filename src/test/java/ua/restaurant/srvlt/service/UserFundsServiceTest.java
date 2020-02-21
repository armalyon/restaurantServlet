package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

@RunWith( MockitoJUnitRunner.class )
public class UserFundsServiceTest {

    private static final String USERNAME = "username";
    private static final String USERNAME_NOT_FOUND = "username";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSWORD = "pwd";
    private static final long ID = 1L;
    private static final long FUNDS = 25L;
    private static LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final User user = new User.Builder()
            .username(USERNAME)
            .name(NAME)
            .surname(SURNAME)
            .password(PASSWORD)
            .registrationDate(LOCAL_DATE_TIME)
            .role(Role.CLIENT)
            .id(ID)
            .funds(FUNDS)
            .build();
    @InjectMocks
    private UserFundsService instance;
    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        when(userDao.findUserByUsername(USERNAME)).thenReturn(Optional.of(user));
        doThrow(UserNotFoundException.class).when(userDao).findUserByUsername(USERNAME_NOT_FOUND);
    }

    @Test
    public void shouldReturnFundsIfUSerExists() throws UserNotFoundException {
        long funds = instance.getFundsByUsername(USERNAME);
        Assert.assertEquals(FUNDS, funds);
    }

    @Test( expected = UserNotFoundException.class )
    public void shouldThrowExceptionIfUserNotFound() throws UserNotFoundException {
        instance.getFundsByUsername(USERNAME_NOT_FOUND);
    }

}
