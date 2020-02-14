package ua.restaurant.srvlt.model.service;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

public class LoginService {
    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public boolean isPasswordCorrect(String username, String password) throws UserNotFoundException {
        User user = userDao.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(LoginService.class.getName(), username));
        return BCrypt.checkpw(password, user.getPassword());
    }

    public Role getUserRoleByUsername(String username) throws UserNotFoundException {
        User user = userDao.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(LoginService.class.getName(), username));
        return user.getRole();
    }

}
