package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.BillDao;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.entity.Order;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.OrderStatement.*;

@RunWith( MockitoJUnitRunner.class )
public class AdminBillServiceTest {

    private static final Order CONFIRMED_ORDER = new Order.Builder()
            .id(1L)
            .orderStatement(CONFIRMED)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final Order REJECTED_ORDER = new Order.Builder()
            .id(2L)
            .orderStatement(REJECTED)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final Order INVOICED_ORDER = new Order.Builder()
            .id(3L)
            .orderStatement(INVOICED)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final long ID_NOT_FOUND = 5;

    @InjectMocks
    private static AdminBillService instance;

    @Mock
    private static OrderDao orderDao;

    @Mock
    private static BillDao billDao;

    @Before
    public void setUp() {
        when(orderDao.findById(CONFIRMED_ORDER.getId())).thenReturn(Optional.of(CONFIRMED_ORDER));
        when(orderDao.findById(REJECTED_ORDER.getId())).thenReturn(Optional.of(REJECTED_ORDER));
        when(orderDao.findById(INVOICED_ORDER.getId())).thenReturn(Optional.of(INVOICED_ORDER));
        doThrow(IdNotFoundExeption.class).when(orderDao).findById(ID_NOT_FOUND);
    }

    @Test
    public void shouldReturnTrueWhenOrderWasNotInvoiced() throws IdNotFoundExeption {
        boolean result = instance.saveNewBill(CONFIRMED_ORDER.getId());
        Assert.assertTrue(result);
    }

    @Test
    public void shouldREturnFalseIfOrderIsRejected() throws IdNotFoundExeption {
        boolean result = instance.saveNewBill(REJECTED_ORDER.getId());
        Assert.assertFalse(result);
    }

    @Test
    public void shouldREturnFalseIfOrderIsInvoiced() throws IdNotFoundExeption {
        boolean result = instance.saveNewBill(INVOICED_ORDER.getId());
        Assert.assertFalse(result);
    }

    @Test( expected = IdNotFoundExeption.class )
    public void shouldThrowExceptionIfOrderIdNotFound() throws IdNotFoundExeption {
        instance.saveNewBill(ID_NOT_FOUND);
    }

}
