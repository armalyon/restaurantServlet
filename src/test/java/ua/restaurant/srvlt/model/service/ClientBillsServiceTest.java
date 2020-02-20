package ua.restaurant.srvlt.model.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.BillStatement;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.model.entity.type.BillStatement.*;
import static ua.restaurant.srvlt.model.entity.type.OrderStatement.WAITING;

@RunWith( MockitoJUnitRunner.class )
public class ClientBillsServiceTest {

    private static final String USERNAME = "username";
    private static final int CURRENT_PAGE = 2;
    private static final int PAGE_SIZE = 5;
    private static final  int TOTAL_BILLS = 7;

    private static final Order ORDER_1 = new Order.Builder()
            .id(11L)
            .menuItem(null)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final Order ORDER_2 = new Order.Builder()
            .id(12L)
            .menuItem(null)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final Bill BILL_1 = new Bill.Builder()
            .id(1L)
            .invoiceDateTime(LocalDateTime.now())
            .paymentDateTime(LocalDateTime.MAX)
            .statement(PAYED)
            .order(ORDER_1)
            .build();

    private static final Bill BILL_2 = new Bill.Builder()
            .id(2L)
            .invoiceDateTime(LocalDateTime.now())
            .paymentDateTime(LocalDateTime.MAX)
            .statement(PAYED)
            .order(ORDER_2)
            .build();

    private List<Bill> bills = new ArrayList<>();
    private Page<Bill> page;


    @InjectMocks
    private ClientBillsService instance;

    @Mock
    private BillDao billDao;

    @Before
    public void setUp() {
        bills.add(BILL_1);
        bills.add(BILL_2);
        page = new Page<>(TOTAL_BILLS, CURRENT_PAGE, PAGE_SIZE, bills);
        when(billDao.countBillsByUsername(USERNAME)).thenReturn(7);
        when(billDao.getBillsByUserNameNewestFirst(
                USERNAME, CURRENT_PAGE, PAGE_SIZE, CURRENT_PAGE*PAGE_SIZE)).thenReturn(bills);
    }

    @Test
    public void shouldReturnNotEmptyPage() {
        Page<Bill> result = instance.getBillsByUsername(USERNAME, CURRENT_PAGE, PAGE_SIZE);
        Assert.assertEquals(page, result);
    }


}