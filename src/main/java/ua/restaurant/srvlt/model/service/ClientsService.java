package ua.restaurant.srvlt.model.service;

import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;

import java.util.List;

import static ua.restaurant.srvlt.model.entity.type.Role.CLIENT;

public class ClientsService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public Page<User> getAllClients(int currentPage, int pageSize) {
        int totalClients = userDao.countUsersByRole(CLIENT);
        int offset = pageSize * currentPage;
        List<User> usersOnPage = userDao.findAllPageByRole(CLIENT, offset, pageSize);
        return new Page<>(totalClients, currentPage, pageSize, usersOnPage);
    }
}
