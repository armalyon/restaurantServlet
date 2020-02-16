package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.pagination.Page;

import java.util.List;

public class ClientBillsService {
    private BillDao billDao = DaoFactory.getInstance().createBillDao();

    public Page<Bill> getBillsByUsername(String username, int currentPage, int pageSize) {
        int offset = pageSize * currentPage;
        int totalBillsByUser = billDao.countBillsByUsername(username);
        List<Bill> bills = billDao.getBillsByUserNameNewestFirst(username, currentPage, pageSize, offset);
        return new Page<>(totalBillsByUser, currentPage, pageSize, bills);
    }
}
