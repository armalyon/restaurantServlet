package ua.restaurant.srvlt.constants;

public interface TextConstants {

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
    String SURNAME_FIELD = "surname";


    //Pages
    String LOGIN_PAGE = "/view/login.jsp";
    String ADMIN_MAIN_PAGE = "/view/admin/adminpage.jsp";
    String CLIENT_MAIN_PAGE = "/view/client/clientpage.jsp";
    String CLIENT_ORDERS_PAGE = "/view/client/clientorders.jsp";
    String DENIED_PAGE = "/view/error/denied.jsp";
    String INDEX_PAGE = "/view/index.jsp";
    String REGISTRATION_PAGE = "/view/registration.jsp";
    String ADMIN_CONFIRMATION_PAGE = "/view/admin/adminconfirmation.jsp";

    //Redirects

    String DENIED_REDIRECT = "redirect:/denied";
    String LOGOUT_REDIRECT = "redirect:/logout";
    String LOGIN_REDIRECT = "redirect:/login";
    String ADMIN_MAIN_REDIRECT = "redirect:/admin";
    String CLIENT_MAIN_REDIRECT = "redirect:/client";
    String ADMIN_CONFIRMATION_REDIRECT = "redirect:/admin_confirmation";

    //Params
    String CREDENTIALS_ERROR_PARAM = "?error=credentials";

}
