package ua.restaurant.srvlt.model.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.MenuItem;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.model.entity.type.OrderStatement.WAITING;
import static ua.restaurant.srvlt.model.entity.type.Role.*;

@RunWith( MockitoJUnitRunner.class )
public class ClientOrderServiceTest {
    private static final String USERNAME = "username";
    private static final int MENU_ITEM_ID = 11;
    private static final int QUANTITY_ENOUGH = 4;
    private static final String NAME = "NAME";
    private static final String SURNAME = "SURNAME";
    private static final String PASSWORD = "PASSWORD";
    private static final String NAME_UA = "NAME_UA";

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
            .id(11L)
            .storageQuantity(5)
            .name(NAME)
            .nameUa(NAME_UA)
            .price(10L)
            .weight(10)
            .build();

    private static final Order ORDER = new Order.Builder()
            .id(0L)
            .menuItem(MENU_ITEM)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
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
    public void setUp(){
        when(menuItemDao.findById(MENU_ITEM_ID)).thenReturn(Optional.of(MENU_ITEM));
        when(userDao.findUserByUsername(USERNAME)).thenReturn(Optional.of(USER));

    }

    @Test(expected = Test.None.class )
    public void shouldNotThrowAnyExceptionWhenOrderSaved()
            throws UserNotFoundException, NotEnoughItemsException, IdNotFoundExeption {
        instance.saveNewOrder(USERNAME, MENU_ITEM_ID, QUANTITY_ENOUGH);
        verify(orderDao).create(Mockito.any());
    }

}