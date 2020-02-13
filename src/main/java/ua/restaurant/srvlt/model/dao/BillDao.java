package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.entity.type.BillStatement;
import ua.restaurant.srvlt.model.pagination.Page;

import java.time.LocalDateTime;

public interface BillDao extends GenericDao<Bill> {

    Page<Bill> getBillsByUserNameNewestFirst(String username, int currentPage, int pageSize);

    void updatePaymentDateStatementById(BillStatement biillStatement, LocalDateTime localDateTime, long id);
}
