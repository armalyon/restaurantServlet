package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.type.BillStatement;

import java.time.LocalDateTime;
import java.util.List;

public interface BillDao extends GenericDao<Bill> {

    List<Bill> getBillsByUserNameNewestFirst(String username, int currentPage, int pageSize, int offset);

    void updatePaymentDateStatementById(BillStatement biillStatement, LocalDateTime localDateTime, long id);

    int countBillsByUsername(String username);
}
