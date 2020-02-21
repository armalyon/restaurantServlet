package ua.restaurant.srvlt.dao;

import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.Role;
import ua.restaurant.srvlt.exception.TransactionException;
import ua.restaurant.srvlt.exception.UserExistsException;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    List<User> findAllPageByRole(Role role, int offset, int pageSize);

    int countUsersByRole(Role role);

    Optional<User> findUserByUsername(String username);

    void createNewUser(User user) throws UserExistsException;

    void transferFunds(String payerUsername, String recieverUsername, long valueToTransfer) throws TransactionException;
}
