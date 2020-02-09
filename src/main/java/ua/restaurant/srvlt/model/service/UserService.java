package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;

public class UserService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();


    public Long getFundsByUsername(String username) {
        return userDao
                .findUserByUsername(username)
                .getFunds();
    }
}
