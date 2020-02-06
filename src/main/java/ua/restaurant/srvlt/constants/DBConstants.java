package ua.restaurant.srvlt.constants;

public interface DBConstants {
    String DB_SET_URL = "db.set.url";
    String DB_USERNAME = "db.username";
    String DB_PASSWORD = "db.password";

    String FIND_USER_BY_USERNAME_PREPARED_STATEMENT = "query.find.user.by.username";
    String FIND__ALL_USERS_BY_ROLE_PREPARED_STATEMENT = "query.find.all.users.by.role";
    String SAVE_USER_INTO_TABLE = "query.insert.user.into.user.table";

    String FIND_MENU_ITEMS_BY_STORAGE_QUANTITY_GREATER_THAN = "query.select.menuitems.st.quantity.greater.than";
    String FIND_MENU_ITEM_BY_ID = "query.find.menu.item.by.id";
    String SAVE_ORDER_INTO_TABLE = "query.insert.order.into.orders.table";
    String FIND_ORDERS_BY_USERNAME_AND_DATE = "query.find.orders.by.username.date";
    String FIND_ORDERS_BY_USERNAME_PAGEABLE = "query.find.orders.by.username.pagable";
    String COUNT_ORDERS_BY_USERNAME = "query.count.orders.by.username";


}
