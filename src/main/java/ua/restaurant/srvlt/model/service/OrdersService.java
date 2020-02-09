package ua.restaurant.srvlt.model.service;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dto.OrdersDTO;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.pagination.Page;

import java.time.LocalDate;

public class OrdersService {
    private static final Logger LOGGER = Logger.getLogger(OrdersService.class);

    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();

   public Page<Order> getOrdersByUserame(String username, int currentPage, int pageSize) {
        return orderDao
                .findAllByUsernamePagable(username, currentPage, pageSize);
    }

    public OrdersDTO getTodayOrdersByUserame(String username) {

        return new OrdersDTO (orderDao
                .findAllByUsernameAndDate(username, LocalDate.now())
        );
    }
}
