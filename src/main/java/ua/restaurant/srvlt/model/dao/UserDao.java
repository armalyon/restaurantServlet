package ua.restaurant.srvlt.model.dao;

import ua.restaurant.srvlt.exceptions.TransactionException;
import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    List<User> findAllPageByRole(Role role, int offset, int pageSize);

    int countUsersByRole(Role role);

    Optional<User> findUserByUsername(String username);

    void createNewUser(User user) throws UserExistsException;

    void transferFunds(String payerUsername, String recieverUsername, long valueToTransfer) throws TransactionException;
}
