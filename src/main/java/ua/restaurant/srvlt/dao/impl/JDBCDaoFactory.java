package ua.restaurant.srvlt.dao.impl;

import ua.restaurant.srvlt.dao.BillDao;
import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.MenuItemDao;

public class JDBCDaoFactory extends DaoFactory {

    @Override
    public JDBCUserDao createUserDao() {
        return new JDBCUserDao();
    }

    @Override
    public MenuItemDao createMenuItemDao() {
        return new JDBCMenuItemDao();
    }

    @Override
    public JDBCOrderDao createOrderDao() {
        return new JDBCOrderDao();
    }

    @Override
    public BillDao createBillDao() {
        return new JDBCBillDao();
    }


}
