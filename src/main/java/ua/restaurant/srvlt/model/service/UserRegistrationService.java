package ua.restaurant.srvlt.model.service;


import org.mindrot.jbcrypt.BCrypt;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.dto.AccountDTO;
import ua.restaurant.srvlt.model.entity.types.Role;
import ua.restaurant.srvlt.exceptions.UserExistsException;

import java.time.LocalDateTime;

public class UserRegistrationService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public boolean saveNewUser(AccountDTO accountDTO) throws UserExistsException {
        User user = new User.Builder()
                .username(accountDTO.getUsername())
                .password(BCrypt.hashpw(accountDTO.getPassword(), BCrypt.gensalt()))
                .name(accountDTO.getName())
                .surname(accountDTO.getSurname())
                .funds(25)
                .role(Role.CLIENT)
                .registrationDate(LocalDateTime.now())
                .build();
        userDao.createNewUser(user);
        return true;
    }
}
