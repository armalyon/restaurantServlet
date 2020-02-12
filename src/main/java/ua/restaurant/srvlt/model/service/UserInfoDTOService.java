package ua.restaurant.srvlt.model.service;


import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.dto.UserInfoDTO;
import ua.restaurant.srvlt.model.entity.User;

public class UserInfoDTOService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();



    public UserInfoDTO getUserInfDTOByUSername(String username) {
        User user = userDao
                .findUserByUsername(username);
        int ordersByUser = orderDao.countOrdersByUsername(username);
        return new UserInfoDTO.Builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .registrationDate(user.getRegistrationDate())
                .totalOrders(ordersByUser)
                .build();
    }

}
