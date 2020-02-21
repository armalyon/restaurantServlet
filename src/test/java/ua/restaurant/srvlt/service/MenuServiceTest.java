package ua.restaurant.srvlt.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import ua.restaurant.srvlt.dao.MenuItemDao;
import ua.restaurant.srvlt.dto.MenuDTO;
import ua.restaurant.srvlt.entity.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith( MockitoJUnitRunner.class )
public class MenuServiceTest {

    private static final MenuItem MENU_ITEM_1 = new MenuItem.Builder()
            .id(1)
            .storageQuantity(3)
            .name("name")
            .nameUa("nameUA")
            .price(5)
            .weight(4)
            .build();

    private static final MenuItem MENU_ITEM_2 = new MenuItem.Builder()
            .id(2)
            .storageQuantity(3)
            .name("name2")
            .nameUa("nameUA2")
            .price(5)
            .weight(4)
            .build();
    private List<MenuItem> items = new ArrayList<>();

    private MenuDTO menuDTO;

    @InjectMocks
    private MenuService instance;

    @Mock
    private MenuItemDao menuItemDao;

    @Before
    public void setUp(){
        items.add(MENU_ITEM_1);
        items.add(MENU_ITEM_2);
        menuDTO = new MenuDTO(items);
        when(menuItemDao.findAllByStorageQuantityGreaterThan(0)).thenReturn(items);
    }

    @Test
    public void shouldReturnMenuDTO(){
        MenuDTO result = instance.getMenu();
        Assert.assertEquals(menuDTO, result);
    }

}