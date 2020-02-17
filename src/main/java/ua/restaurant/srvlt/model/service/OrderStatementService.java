package ua.restaurant.srvlt.model.service;


import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.type.OrderStatement;

public class OrderStatementService {

    OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    public boolean updateOrderStatement(OrderStatement statement, Long orderId) {
        orderDao.updateOrderStatementById(statement, orderId);
        return true;
    }

}
