package ua.restaurant.srvlt.model.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import static ua.restaurant.srvlt.constants.DBConstants.*;

public class ConnectionPoolHolder {
    private static ResourceBundle bundle = ResourceBundle.getBundle("dbsettings");
    private static volatile DataSource dataSource;

    public static Connection getConnection() throws SQLException {
        return getDataSource().getConnection();
    }

    private static DataSource getDataSource() {
        if (dataSource == null) {
            synchronized (ConnectionPoolHolder.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl(bundle.getString(DB_SET_URL));
                    ds.setUsername(bundle.getString(DB_USERNAME));
                    ds.setPassword(bundle.getString(DB_PASSWORD));
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;
    }

}