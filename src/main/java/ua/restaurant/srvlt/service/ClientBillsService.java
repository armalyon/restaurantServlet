package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dao.BillDao;
import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.entity.Bill;

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
