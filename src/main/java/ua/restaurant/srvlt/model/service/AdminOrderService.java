package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

import java.util.List;

public class AdminOrderService {
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    public Page<Order> getOrdersByStatement(OrderStatement statement, int currentPage, int pageSize) {
        int totalOrders = orderDao.countOrdersByStatement(statement);
        int offset = pageSize * currentPage;
        List<Order> ordersOnPage = orderDao.findAllByOrderStatementPageable(statement, offset, pageSize);
        return new Page<>(totalOrders, currentPage, pageSize, ordersOnPage);
    }
}
