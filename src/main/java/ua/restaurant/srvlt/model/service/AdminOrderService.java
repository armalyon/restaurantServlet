package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;
import ua.restaurant.srvlt.model.pagination.Page;

public class AdminOrderService {
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    public Page<Order> getOrdersByStatement(OrderStatement statement, int currentPage, int pageSize) {
        return orderDao
                .findAllByOrderStatementOrderByDate(statement, currentPage, pageSize);
    }
}
