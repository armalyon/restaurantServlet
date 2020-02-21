package ua.restaurant.srvlt.dao;


import ua.restaurant.srvlt.dao.impl.JDBCDaoFactory;

public abstract class DaoFactory {

    private static DaoFactory daoFactory;

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }

    public abstract UserDao createUserDao();

    public abstract MenuItemDao createMenuItemDao();

    public abstract OrderDao createOrderDao();

    public abstract BillDao createBillDao();
}
