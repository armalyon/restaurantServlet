package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.types.Role;
import ua.restaurant.srvlt.exceptions.UserExistsException;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    List<User> findAllByRole(Role role);
    User findUserByUsername(String username);
    void createNewUser (User user) throws UserExistsException;
}
