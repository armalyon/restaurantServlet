package ua.restaurant.srvlt.model.service;


import ua.restaurant.srvlt.dto.OrdersDTO;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;

import java.time.LocalDate;
import java.util.List;

public class OrdersService {
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    public Page<Order> getOrdersByUserame(String username, int currentPage, int pageSize) {
        int totalOrders = orderDao.countOrdersByUsername(username);
        int offset = pageSize * currentPage;
        List<Order> ordersOnPage = orderDao.findAllByUsernamePageable(username, offset, pageSize);
        return new Page<>(totalOrders, currentPage, pageSize, ordersOnPage);
    }

    public OrdersDTO getTodayOrdersByUserame(String username) {
        return new OrdersDTO(orderDao
                .findAllByUsernameAndDate(username, LocalDate.now())
        );
    }
}
