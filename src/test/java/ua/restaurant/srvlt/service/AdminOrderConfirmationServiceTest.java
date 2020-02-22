package ua.restaurant.srvlt.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.entity.MenuItem;
import ua.restaurant.srvlt.entity.Order;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughItemsException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.OrderStatement.CONFIRMED;
import static ua.restaurant.srvlt.entity.type.OrderStatement.WAITING;

@RunWith( MockitoJUnitRunner.class )
public class AdminOrderConfirmationServiceTest {

    private static final long ID_NOT_FOUND = 38;

    private static final MenuItem MENU_ITEM = new MenuItem.Builder()
            .id(11L)
            .storageQuantity(5)
            .build();

    private static final MenuItem MENU_ITEM_LOW_QUANTITY = new MenuItem.Builder()
            .id(12L)
            .storageQuantity(4)
            .build();

    private static final Order WAITING_ORDER = new Order.Builder()
            .id(1L)
            .menuItem(MENU_ITEM)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final Order CONFIRMED_ORDER = new Order.Builder()
            .id(2L)
            .menuItem(MENU_ITEM)
            .orderStatement(CONFIRMED)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(2)
            .totalPrice(10)
            .build();

    private static final Order TOO_MANY_ORDERED_ORDER = new Order.Builder()
            .id(3L)
            .menuItem(MENU_ITEM_LOW_QUANTITY)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(6)
            .totalPrice(10)
            .build();

    @InjectMocks
    private AdminOrderConfirmationService instance;

    @Mock
    private OrderDao orderDao;

    @Before
    public void setUp() {
        when(orderDao.findById(WAITING_ORDER.getId())).thenReturn(Optional.of(WAITING_ORDER));
        when(orderDao.findById(TOO_MANY_ORDERED_ORDER.getId())).thenReturn(Optional.of(TOO_MANY_ORDERED_ORDER));
        when(orderDao.findById(CONFIRMED_ORDER.getId())).thenReturn(Optional.of(CONFIRMED_ORDER));
        doThrow(IdNotFoundExeption.class).when(orderDao).findById(ID_NOT_FOUND);
    }

    @Test
    public void shouldCanBeConfirmedIfOrderHasWaitingStatementAndStorageQuantityEnough() throws IdNotFoundExeption, NotEnoughItemsException {
        boolean result = instance.isCanBeConfirmed(WAITING_ORDER.getId());
        Assert.assertTrue(result);
    }

    @Test( expected = NotEnoughItemsException.class )
    public void shouldThrowExceptionIfNotEnoughStorageQantity() throws IdNotFoundExeption, NotEnoughItemsException {
        instance.isCanBeConfirmed(TOO_MANY_ORDERED_ORDER.getId());
    }

    @Test
    public void shouldReturnFalseIfOrderIsConfirmed() throws IdNotFoundExeption, NotEnoughItemsException {
        boolean result = instance.isCanBeConfirmed(CONFIRMED_ORDER.getId());
        Assert.assertFalse(result);
    }

    @Test( expected = IdNotFoundExeption.class )
    public void shouldThrowExceptionIfOrderNotFound() throws IdNotFoundExeption {
        instance.confirmOrder(ID_NOT_FOUND, WAITING_ORDER.getQuantity());
    }

    @Test( expected = Test.None.class )
    public void shouldNotThrowExceptionWhenTyingToConfirmOrderIfOrderIsCorrect() throws IdNotFoundExeption {
        instance.confirmOrder(WAITING_ORDER.getId(), WAITING_ORDER.getQuantity());
    }


}
