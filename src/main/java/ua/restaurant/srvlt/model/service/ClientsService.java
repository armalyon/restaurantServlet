package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;

import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

import java.util.List;

public class ClientsService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public List<User> getAllClients(){
            return  userDao.findAllByRole(Role.CLIENT);

    }
}
