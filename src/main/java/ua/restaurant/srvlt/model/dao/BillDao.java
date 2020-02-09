package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.pagination.Page;

public interface BillDao extends GenericDao<Bill> {

    Page<Bill> getBillsByUserNameNewestFirst(String username, int currentPage, int pageSize);
}
