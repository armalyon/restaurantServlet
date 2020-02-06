package ua.restaurant.srvlt.model.service;

import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.types.Role;

public class LoginService {
    private static final Logger LOGGER = Logger.getLogger(LoginService.class);

    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public boolean isUserCanBeLoggedIn(String username, String password) {
        User user = userDao.findUserByUsername(username);
        if (user == null) return false;
        else
            return BCrypt.checkpw(password, user.getPassword());
    }

    public Role getUserRoleByUsername(String username) {
        User user = userDao.findUserByUsername(username);
        if (user == null) return null;
        return user.getRole();
    }

}
