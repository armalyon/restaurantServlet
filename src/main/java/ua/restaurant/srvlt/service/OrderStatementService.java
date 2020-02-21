package ua.restaurant.srvlt.service;


import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.entity.type.OrderStatement;

public class OrderStatementService {

    OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    public boolean updateOrderStatement(OrderStatement statement, Long orderId) {
        orderDao.updateOrderStatementById(statement, orderId);
        return true;
    }

}
