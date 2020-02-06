package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;
import ua.restaurant.srvlt.model.pagination.Page;

import java.awt.print.Pageable;

public class AdminOrderService {
    private OrderDao orderRepository = DaoFactory.getInstance().createOrderDao();

    public Page<Order> getOrdersByStatement(OrderStatement statement, int currentPage, int pageSize) {
        return orderRepository
                .findAllByOrderStatementOrderByDate(statement, currentPage, pageSize);
    }
}
