package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;

public class UserFundsService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public Long getFundsByUsername(String username) throws UserNotFoundException {
        return userDao
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("ERROR USERNOT FOUND ", username))
                .getFunds();
    }
}
