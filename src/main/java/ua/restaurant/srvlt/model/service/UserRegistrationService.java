package ua.restaurant.srvlt.model.service;


import org.mindrot.jbcrypt.BCrypt;
import ua.restaurant.srvlt.dto.AccountDTO;
import ua.restaurant.srvlt.exceptions.UserExistsException;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.User;
import ua.restaurant.srvlt.model.entity.type.Role;

import java.time.LocalDateTime;

public class UserRegistrationService {
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public boolean saveNewUser(AccountDTO accountDTO) throws UserExistsException {
        User user = buildUser(accountDTO);
        userDao.createNewUser(user);
        return true;
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
