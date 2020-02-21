package ua.restaurant.srvlt.service;

import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.entity.User;

import java.util.List;

import static ua.restaurant.srvlt.entity.type.Role.CLIENT;

public class ClientsService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public Page<User> getAllClients(int currentPage, int pageSize) {
        int totalClients = userDao.countUsersByRole(CLIENT);
        int offset = pageSize * currentPage;
        List<User> usersOnPage = userDao.findAllPageByRole(CLIENT, offset, pageSize);
        return new Page<>(totalClients, currentPage, pageSize, usersOnPage);
    }
}
