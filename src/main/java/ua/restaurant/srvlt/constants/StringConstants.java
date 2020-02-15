package ua.restaurant.srvlt.constants;

public interface StringConstants {

    //Attributes
    String ROLE_ATTRIBUTE = "role";
    String USERNAME_ATTRIBUTE = "username";
    String NAME_ATTRIBUTE = "name";
    String SURNAME_ATTRIBUTE = "surname";
    String PASSWORD_CONFIRMATION_ATTRIBUTE = "passwordConfirmation";
    String PASSWORD_ATTRIBUTE = "password";
    String LOGGED_USERS_ATTRIBUTE = "loggedUsers";
    String MENU_ITEM_ID_ATTRIBUTE = "menuItemId";
    String QUANTITY_ATTRIBUTE = "quantity";
    String MENU_DTO_ATTRIBUTE = "menuDTO";
    String ORDERS_DTO_ATTRIBUTE = "ordersDTO";
    String PAGE_ATTRIBUTE = "page";
    String ID_ATTRIBUTE = "id";
    String FUNDS_ATTRIBUTE = "funds";
    String USER_INFO_DTO_ATTRIBUTE = "userInfoDTO";
    String ADMIN_USERNAME = "admin";


    String REGISTRATION = "registration";
    String REGISTER = "register";
    String LOGIN = "login";
    String ERROR = "error";
    String LOGOUT = "logout";
    String DENIED = "denied";

    //Fields
    String PASSWORD_FIELD = "password";
    String USERNAME_FIELD = "username";
    String NAME_FIELD = "name";


    //Pages
    String LOGIN_PAGE = "/view/login.jsp";
    String ADMIN_MAIN_PAGE = "/view/admin/adminpage.jsp";
    String CLIENT_MAIN_PAGE = "/view/client/clientpage.jsp";
    String CLIENT_ORDERS_PAGE = "/view/client/clientorders.jsp";
    String DENIED_PAGE = "/view/error/denied.jsp";
    String INDEX_PAGE = "/view/index.jsp";
    String REGISTRATION_PAGE = "/view/registration.jsp";
    String ADMIN_CONFIRMATION_PAGE = "/view/admin/adminconfirmation.jsp";
    String ADMIN_CONFIRMED_PAGE = "/view/admin/adminconfirmed.jsp";
    String CLIENT_BILLS_PAGE = "/view/client/clientbills.jsp";
    String ADMIN_USERS_PAGE = "/view/admin/adminusers.jsp";
    String ADMIN_STATS_PAGE = "/view/admin/adminstats.jsp";

    //Redirects

    String DENIED_REDIRECT = "redirect:/denied";
    String LOGOUT_REDIRECT = "redirect:/logout";
    String LOGIN_REDIRECT = "redirect:/login";
    String ADMIN_MAIN_REDIRECT = "redirect:/admin";
    String CLIENT_MAIN_REDIRECT = "redirect:/client";
    String CLIENT_MAIN_ITEM_ERROR_REDIRECT = "redirect:/client?error=item";
    String CLIENT_MAIN_QUANTITY_ERROR_REDIRECT = "redirect:/client?error=quantity";
    String ADMIN_CONFIRMATION_REDIRECT = "redirect:/admin_confirmation";
    String ADMIN_CONFIRMATION_ERROR_ITEMS_REDIRECT = "redirect:/admin_confirmation?error=items";
    String ADMIN_CONFIRMATION_ERROR_DB_REDIRECT = "redirect:/admin_confirmation?error=db";
    String ADMIN_CONFIRMED_REDIRECT = "redirect:/admin_confirmed";
    String CLIENT_BILLS_REDIRECT = "redirect:/client_bills";
    String CLIENT_BILLS_ERROR_FUNDS_REDIRECT = "redirect:/client_bills?error=funds";
    String CLIENT_BILLS_ERROR_REDIRECT = "redirect:/client_bills?error=unknown";
    String ADMIN_CONFIRMED_REDIRECT_ERROR = "redirect:/admin_confirmed?error=order";


    //Params
    String CREDENTIALS_ERROR_PARAM = "?error=credentials";

}
