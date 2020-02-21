package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.exception.UserNotFoundException;

public class UserFundsService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public Long getFundsByUsername(String username) throws UserNotFoundException {
        return userDao
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("ERROR USERNOT FOUND ", username))
                .getFunds();
    }
}
