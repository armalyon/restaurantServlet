package ua.restaurant.srvlt.model.service;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.Order;
import ua.restaurant.srvlt.model.entity.type.BillStatement;

import java.time.LocalDateTime;

import static ua.restaurant.srvlt.model.entity.type.OrderStatement.INVOICED;
import static ua.restaurant.srvlt.model.entity.type.OrderStatement.REJECTED;

public class AdminBillService {
    private static final Logger LOGGER = Logger.getLogger(AdminBillService.class);
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();
    private BillDao billDao = DaoFactory.getInstance().createBillDao();

    public boolean saveNewBill(Long orderId) throws IdNotFoundExeption {
        Order order = orderDao
                .findById(orderId).orElseThrow(() -> new IdNotFoundExeption(AdminBillService.class.getName(), orderId));
        if (order.getOrderStatement()
                .equals(INVOICED ) || order.getOrderStatement().equals(REJECTED)) {
            return false;
        } else
            billDao.create(createNewBill(order));
        return true;
    }


    private Bill createNewBill(Order order) {
        return new Bill.Builder()
                .invoiceDateTime(
                        LocalDateTime.now()
                )
                .order(order)
                .statement(BillStatement.INVOICE)
                .build();
    }

}
