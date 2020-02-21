package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.dto.UserInfoDTO;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.Role;
import ua.restaurant.srvlt.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class UserInfoDTOServiceTest {

    private static final String USERNAME_NOT_REGISTERED = "username2";
    private static final String USERNAME = "username";
    private static final int ORDERS_BY_CLIENT = 50;
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PASSWORD = "pwd";
    private static final long ID = 1L;
    private static final long FUNDS = 25L;
    private static final User user = new User.Builder()
            .username(USERNAME)
            .name(NAME)
            .surname(SURNAME)
            .password(PASSWORD)
            .registrationDate(LocalDateTime.now())
            .role(Role.CLIENT)
            .id(ID)
            .funds(FUNDS)
            .build();
    private static LocalDateTime LOCAL_DATE_TIME = LocalDateTime.now();
    private static final UserInfoDTO userInfo = new UserInfoDTO.Builder()
            .name(NAME)
            .surname(SURNAME)
            .registrationDate(LOCAL_DATE_TIME)
            .totalOrders(ORDERS_BY_CLIENT)
            .username(USERNAME)
            .build();

    @InjectMocks
    private UserInfoDTOService instance;

    @Mock
    private UserDao userDao;

    @Mock
    private OrderDao orderDao;

    @Before
    public void setUp() {
        when(userDao.findUserByUsername(USERNAME)).thenReturn(Optional.of(user));
        when(orderDao.countOrdersByUsername(USERNAME)).thenReturn(ORDERS_BY_CLIENT);
        doThrow(UserNotFoundException.class).when(orderDao).countOrdersByUsername(USERNAME_NOT_REGISTERED);
        doThrow(UserNotFoundException.class).when(userDao).findUserByUsername(USERNAME_NOT_REGISTERED);
    }


    @Test
    public void shouldReturnProperValueIfUsernameCorrect() throws UserNotFoundException {
        UserInfoDTO userInfoDTO = instance.getUserInfDTOByUSername(USERNAME);
        Assert.assertEquals(userInfoDTO, userInfo);
    }

    @Test( expected = UserNotFoundException.class )
    public void shouldThrowExceptionIfUserNotFound() throws UserNotFoundException {
        instance.getUserInfDTOByUSername(USERNAME_NOT_REGISTERED);
    }

}
