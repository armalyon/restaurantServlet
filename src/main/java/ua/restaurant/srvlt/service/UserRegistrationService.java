package ua.restaurant.srvlt.service;


import org.mindrot.jbcrypt.BCrypt;
import ua.restaurant.srvlt.dao.DaoFactory;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.entity.User;
import ua.restaurant.srvlt.entity.type.Role;
import ua.restaurant.srvlt.exception.UserExistsException;

import java.time.LocalDateTime;

public class UserRegistrationService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public void saveNewUser(AccountDTO accountDTO) throws UserExistsException {
        User user = buildUser(accountDTO);
        userDao.createNewUser(user);
    }

    private User buildUser(AccountDTO accountDTO) {
        return new User.Builder()
                .username(accountDTO.getUsername())
                .password(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()))
                .name(accountDTO.getName())
                .surname(accountDTO.getSurname())
                .funds(25)
                .role(Role.CLIENT)
                .registrationDate(LocalDateTime.now())
                .build();
    }
}
