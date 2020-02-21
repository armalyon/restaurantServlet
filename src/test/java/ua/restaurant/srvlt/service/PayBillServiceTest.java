package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.BillDao;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.entity.Bill;
import ua.restaurant.srvlt.entity.Order;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.BillStatement;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughFundsException;
import ua.restaurant.srvlt.exception.TransactionException;
import ua.restaurant.srvlt.exception.UserNotFoundException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.OrderStatement.INVOICED;

@RunWith( MockitoJUnitRunner.class )
public class PayBillServiceTest {

    private static final String USERNAME = "username";
    private static final String USERNAME_NO_FUNDS = "username3";
    private static final String USERNAME_2 = "username2";
    private static final long BILL_ID = 1;
    private static final long BILL_ID_FUNDS_NOT_ENOUGH = 2;
    private static final long BILL_PAYED_ID = 3;

    private static final User USER = new User.Builder()
            .username(USERNAME)
            .id(21L)
            .funds(10)
            .build();

    private static final User USER_FUNDS_NOT_ENOUGH = new User.Builder()
            .username(USERNAME_NO_FUNDS)
            .id(21L)
            .funds(9)
            .build();

    private static final Order ORDER = new Order.Builder()
            .orderStatement(INVOICED)
            .quantity(5)
            .user(USER)
            .totalPrice(10)
            .id(11)
            .build();

    private static final Order ORDER_NOT_ENOUGH_TEST = new Order.Builder()
            .orderStatement(INVOICED)
            .quantity(5)
            .user(USER_FUNDS_NOT_ENOUGH)
            .totalPrice(10)
            .id(11)
            .build();

    private static final Bill BILL = new Bill.Builder()
            .id(BILL_ID)
            .statement(BillStatement.INVOICE)
            .invoiceDateTime(LocalDateTime.MIN)
            .order(ORDER)
            .build();

    private static final Bill BILL_PAYED = new Bill.Builder()
            .id(BILL_PAYED_ID)
            .statement(BillStatement.PAYED)
            .invoiceDateTime(LocalDateTime.MIN)
            .order(ORDER)
            .build();

    private static final Bill BILL_FUNDS_NOT_ENOUGH = new Bill.Builder()
            .id(BILL_ID_FUNDS_NOT_ENOUGH)
            .statement(BillStatement.INVOICE)
            .invoiceDateTime(LocalDateTime.MIN)
            .order(ORDER_NOT_ENOUGH_TEST)
            .build();

    @InjectMocks
    private PayBillService instance;

    @Mock
    private BillDao billDao;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        doThrow(UserNotFoundException.class).when(userDao).findUserByUsername(USERNAME_2);
        when(billDao.findById(BILL.getId())).thenReturn(Optional.of(BILL));
        when(userDao.findUserByUsername(USER.getUsername())).thenReturn(Optional.of(USER));
        when(billDao.findById(BILL_FUNDS_NOT_ENOUGH.getId())).thenReturn(Optional.of(BILL_FUNDS_NOT_ENOUGH));
        when(userDao.findUserByUsername(USER_FUNDS_NOT_ENOUGH.getUsername())).thenReturn(Optional.of(USER_FUNDS_NOT_ENOUGH));
        when(billDao.findById(BILL_PAYED.getId())).thenReturn(Optional.of(BILL_PAYED));
    }

    @Test
    public void shouldReturnTrueWhenFundsEnoughAndStatementNotPayed()
            throws UserNotFoundException, TransactionException, NotEnoughFundsException, IdNotFoundExeption {
        boolean result = instance.payBill(BILL_ID, USER.getUsername());
        Assert.assertTrue(result);
    }

    @Test( expected = NotEnoughFundsException.class )
    public void shouldThrowExceptionWhenFundsNotEnough()
            throws UserNotFoundException, TransactionException, NotEnoughFundsException, IdNotFoundExeption {
        instance.payBill(BILL_ID_FUNDS_NOT_ENOUGH, USER_FUNDS_NOT_ENOUGH.getUsername());
    }

    @Test
    public void shouldReturFalseWhenBillPayed()
            throws UserNotFoundException, TransactionException, NotEnoughFundsException, IdNotFoundExeption {
        boolean result = instance.payBill(BILL_PAYED.getId(), USER.getUsername());
        Assert.assertFalse(result);
    }

    @Test
    public void shouldReturnFalseIfUsernamedoesNotMatchBill() throws UserNotFoundException, TransactionException, NotEnoughFundsException, IdNotFoundExeption {
        boolean result = instance.payBill(BILL.getId(), USERNAME_2);
        Assert.assertFalse(result);
    }

}