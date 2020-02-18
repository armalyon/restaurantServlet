package ua.restaurant.srvlt.model.service;


import ua.restaurant.srvlt.dto.UserInfoDTO;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.OrderDao;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;

public class UserInfoDTOService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();
    private OrderDao orderDao = DaoFactory.getInstance().createOrderDao();


    public UserInfoDTO getUserInfDTOByUSername(String username) throws UserNotFoundException {
        User user = userDao
                .findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(UserInfoDTOService.class.getName(), username));
        int ordersByUser = orderDao.countOrdersByUsername(username);
        return buildUserInfoDTO(user, ordersByUser);
    }

    private UserInfoDTO buildUserInfoDTO(User user, int ordersByUser) {
        return new UserInfoDTO.Builder()
                .username(user.getUsername())
                .name(user.getName())
                .surname(user.getSurname())
                .registrationDate(user.getRegistrationDate())
                .totalOrders(ordersByUser)
                .build();
    }

}
