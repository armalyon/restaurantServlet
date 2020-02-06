package ua.restaurant.srvlt.model.dao.impl;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dao.OrderDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private static final Logger LOGGER = Logger.getLogger(JDBCDaoFactory.class);

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


}
