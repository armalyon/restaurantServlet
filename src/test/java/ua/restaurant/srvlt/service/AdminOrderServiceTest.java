package ua.restaurant.srvlt.service;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.entity.MenuItem;
import ua.restaurant.srvlt.entity.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.OrderStatement.WAITING;

@RunWith( MockitoJUnitRunner.class )
public class AdminOrderServiceTest {
    private static final int CURRENT_PAGE = 1;
    private static final int PAGE_SIZE = 5;

    private static final MenuItem MENU_ITEM = new MenuItem.Builder()
            .id(11L)
            .storageQuantity(5)
            .build();

    private static final Order ORDER_1 = new Order.Builder()
            .id(1L)
            .menuItem(MENU_ITEM)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static final Order ORDER_2 = new Order.Builder()
            .id(2L)
            .menuItem(MENU_ITEM)
            .orderStatement(WAITING)
            .date(LocalDate.now())
            .time(LocalTime.NOON)
            .quantity(5)
            .totalPrice(10)
            .build();

    private static List<Order> orders = new ArrayList<>();

    private static Page<Order> notEmptyPage;

    @InjectMocks
    private AdminOrderService instantance;

    @Mock
    private OrderDao orderDao;

    @Before
    public void setUp() {
        orders.add(ORDER_1);
        orders.add(ORDER_2);
        notEmptyPage = new Page<>(2, CURRENT_PAGE, PAGE_SIZE, orders);
        when(orderDao.findAllByOrderStatementPageable(WAITING, CURRENT_PAGE * PAGE_SIZE, PAGE_SIZE)).thenReturn(orders);
        when(orderDao.countOrdersByStatement(WAITING)).thenReturn(2);
    }

    @Test
    public void shouldReturnNotEmptyPage() {
        Page<Order> result = instantance.getOrdersByStatement(WAITING, CURRENT_PAGE, PAGE_SIZE);
        Assert.assertEquals(notEmptyPage, result);
    }
}
