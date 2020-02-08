package ua.restaurant.srvlt.model.service;


import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.exceptions.NotEnoughItemsException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.types.OrderStatement;

import static ua.restaurant.srvlt.model.entity.types.OrderStatement.CONFIRMED;

public class OrderStatementService {

    OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

    public boolean updateOrderStatement(OrderStatement statement, Long orderId) {
       orderDao.updateOrderStatementById(statement, orderId);
        return true;
    }

}
