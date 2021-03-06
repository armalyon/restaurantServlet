package ua.restaurant.srvlt.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.MenuItemDao;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.entity.MenuItem;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughItemsException;
import ua.restaurant.srvlt.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static ua.restaurant.srvlt.entity.type.Role.CLIENT;

@RunWith( MockitoJUnitRunner.class )
public class ClientOrderServiceTest {
    private static final String USERNAME = "username";
    private static final int MENU_ITEM_ID = 11;
    private static final int MENU_ITEM_ID_NOT_FOUND = 111;
    private static final int QUANTITY_ENOUGH = 5;
    private static final int QUANTITY_NOT_ENOUGH = 6;
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String NAME_UA = "NAME_UA";
    private static final String USERNAME_NOT_FOUND = "USERNAME_NOT_FOUND";

    private static final User USER = new User.Builder()
            .id(21L)
            .funds(10)
            .name(NAME)
            .surname(SURNAME)
            .registrationDate(LocalDateTime.MIN)
            .role(CLIENT)
            .username(USERNAME)
            .password(PASSWORD)
            .build();

    private static final MenuItem MENU_ITEM = new MenuItem.Builder()
            .id(MENU_ITEM_ID)
            .storageQuantity(5)
            .name(NAME)
            .nameUa(NAME_UA)
            .price(10L)
            .weight(10)
            .build();

    @InjectMocks
    private ClientOrderService instance;

    @Mock
    private OrderDao orderDao;

    @Mock
    private UserDao userDao;

    @Mock
    private MenuItemDao menuItemDao;

    @Before
    public void setUp() {
        when(menuItemDao.findById(MENU_ITEM_ID)).thenReturn(Optional.of(MENU_ITEM));
        when(userDao.findUserByUsername(USERNAME)).thenReturn(Optional.of(USER));
        doThrow(UserNotFoundException.class).when(userDao).findUserByUsername(USERNAME_NOT_FOUND);
        doThrow(IdNotFoundExeption.class).when(menuItemDao).findById(MENU_ITEM_ID_NOT_FOUND);
    }

    @Test( expected = Test.None.class )
    public void shouldNotThrowAnyExceptionWhenOrderSaved()
            throws UserNotFoundException, NotEnoughItemsException, IdNotFoundExeption {
        instance.saveNewOrder(USERNAME, MENU_ITEM_ID, QUANTITY_ENOUGH);
        verify(orderDao).create(Mockito.any());
    }

    @Test( expected = UserNotFoundException.class )
    public void shouldThrowUserNotFoundExceptionWhenUsernameNotFound()
            throws UserNotFoundException, NotEnoughItemsException, IdNotFoundExeption {
        instance.saveNewOrder(USERNAME_NOT_FOUND, MENU_ITEM_ID, QUANTITY_ENOUGH);
    }

    @Test( expected = IdNotFoundExeption.class )
    public void shouldThrowIdNotFoundExceptionWhenMenuItemIdNotFound()
            throws UserNotFoundException, NotEnoughItemsException, IdNotFoundExeption {
        instance.saveNewOrder(USERNAME, MENU_ITEM_ID_NOT_FOUND, QUANTITY_ENOUGH);
    }

    @Test( expected = NotEnoughItemsException.class )
    public void shouldThrwNotEnoughItemsExceptionWhenStorageQuantityNotEnough() throws UserNotFoundException, NotEnoughItemsException, IdNotFoundExeption {
        instance.saveNewOrder(USERNAME, MENU_ITEM_ID, QUANTITY_NOT_ENOUGH);
    }
}