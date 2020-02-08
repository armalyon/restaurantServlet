package ua.restaurant.srvlt.controller.command.type;

import ua.restaurant.srvlt.controller.command.*;

public enum Command {
    ADMIN(new AdminPageCommand()),
    CLIENT( new ClientPageCommand()),
    DENIED(new DeniedCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationPageCommand()),
    REGISTER(new RegisterCommand()),
    CLIENT_ORDER( new ClientOrderCommand()),
    CLIENT_ORDERS( new ClientOrdersPageCommand()),
    ADMIN_CONFIRMATION(new AdminConfirmationPageCommand()),
    ADMIN_REJECTORDER(new AdminRejectOrderCommand()),
    ADMIN_CONFIRMORDER(new AdminConfirmOrderCommand()),
    ADMIN_CONFIRMED(new AdminConfirmedOrdersPageCommand()),
    ADMIN_BILL(new AdminBillCommand());

    private ua.restaurant.srvlt.controller.command.Command command;

    Command(ua.restaurant.srvlt.controller.command.Command command) {
        this.command = command;
    }

    public ua.restaurant.srvlt.controller.command.Command getCommand() {
        return command;
    }
}
