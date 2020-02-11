package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;
import ua.restaurant.srvlt.model.pagination.Page;

import java.util.List;

public interface UserDao extends GenericDao<User> {

    List<User> findAllPageByRole(Role role, int offset, int pageSize);

    int countUsersByRole(Role role);

    User findUserByUsername(String username);

    void createNewUser(User user) throws UserExistsException;
}
