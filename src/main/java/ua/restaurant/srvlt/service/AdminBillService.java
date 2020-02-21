package ua.restaurant.srvlt.service;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.dao.BillDao;
import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.entity.Bill;
import ua.restaurant.srvlt.entity.Order;
import ua.restaurant.srvlt.entity.type.BillStatement;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;

import java.time.LocalDateTime;

import static ua.restaurant.srvlt.entity.type.OrderStatement.INVOICED;
import static ua.restaurant.srvlt.entity.type.OrderStatement.REJECTED;

public class AdminBillService {
    private static final Logger LOGGER = Logger.getLogger(AdminBillService.class);
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();
    private BillDao billDao = DaoFactory.getInstance().createBillDao();

    public boolean saveNewBill(Long orderId) throws IdNotFoundExeption {
        Order order = orderDao
                .findById(orderId).orElseThrow(() -> new IdNotFoundExeption(AdminBillService.class.getName(), orderId));
        if (order.getOrderStatement()
                .equals(INVOICED) || order.getOrderStatement().equals(REJECTED)) {
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
