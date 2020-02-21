package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.UserDao;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.entity.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static ua.restaurant.srvlt.entity.type.Role.CLIENT;

@RunWith( MockitoJUnitRunner.class )
public class ClientsServiceTest {

    private static final int CLIENTS_COUNT = 7;
    private static final int CURRENT_PAGE = 2;
    private static final int PAGE_SIZE = 5;
    private static final User USER_1 = new User.Builder()
            .id(11L)
            .funds(25)
            .name("Name")
            .surname("Surname")
            .password("password")
            .registrationDate(LocalDateTime.MIN)
            .username("username 1")
            .role(CLIENT)
            .build();
    private static final User USER_2 = new User.Builder()
            .id(12L)
            .funds(25)
            .name("Name 2")
            .surname("Surname 2")
            .password("password 2")
            .registrationDate(LocalDateTime.MIN)
            .username("username 2")
            .role(CLIENT)
            .build();
    private List<User> clients = new ArrayList<>();
    private Page<User> page;


    @InjectMocks
    private ClientsService instance;

    @Mock
    private UserDao userDao;

    @Before
    public void setUp() {
        clients.add(USER_1);
        clients.add(USER_2);
        page = new Page<>(CLIENTS_COUNT, CURRENT_PAGE, PAGE_SIZE, clients);
        when(userDao.countUsersByRole(CLIENT)).thenReturn(CLIENTS_COUNT);
        when(userDao.findAllPageByRole(CLIENT, PAGE_SIZE * CURRENT_PAGE, PAGE_SIZE)).thenReturn(clients);
    }

    @Test
    public void shouldReturnPage() {
        Page<User> result = instance.getAllClients(CURRENT_PAGE, PAGE_SIZE);
        Assert.assertEquals(page, result);
    }

}