package ua.restaurant.srvlt.model.dao.impl;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.MenuItemDao;
import ua.restaurant.srvlt.model.dao.OrderDao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private static final Logger LOGGER = Logger.getLogger(JDBCDaoFactory.class);


    @Override
    public JDBCUserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public MenuItemDao createMenuItemDao() {
        return new JDBCMenuItemDao(getConnection());
    }

    @Override
    public JDBCOrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            LOGGER.warn("connction error" + e.getMessage());
            throw new RuntimeException(e);
        }
    }


}
