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
import ua.restaurant.srvlt.entity.Order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.OrderStatement.WAITING;

@RunWith( MockitoJUnitRunner.class )
public class OrdersServiceTest {

    private static final String USERNAME = "username";
    private static final int CURRENT_PAGE = 2;
    private static final int PAGE_SIZE = 5;
    private static final int TOTAL_ORDERS = 7;

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

    private List<Order> orders = new ArrayList<>();
    private Page<Order> page;

    @InjectMocks
    private OrdersService instance;

    @Mock
    private OrderDao orderDao;

    @Before
    public void setUp() {
        orders.add(ORDER_1);
        orders.add(ORDER_2);
        page = new Page<>(TOTAL_ORDERS, CURRENT_PAGE, PAGE_SIZE, orders);
        when(orderDao.countOrdersByUsername(USERNAME)).thenReturn(7);
        when(orderDao.findAllByUsernamePageable(
                USERNAME, CURRENT_PAGE * PAGE_SIZE, PAGE_SIZE)).thenReturn(orders);
    }

    @Test
    public void shouldReturnPage() {
        Page<Order> result = instance.getOrdersByUserame(USERNAME, CURRENT_PAGE, PAGE_SIZE);
        Assert.assertEquals(page, result);
    }

}