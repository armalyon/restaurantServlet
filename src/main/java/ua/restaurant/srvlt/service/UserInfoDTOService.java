package ua.restaurant.srvlt.service;


import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.OrderDao;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.dto.UserInfoDTO;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.exception.UserNotFoundException;

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
