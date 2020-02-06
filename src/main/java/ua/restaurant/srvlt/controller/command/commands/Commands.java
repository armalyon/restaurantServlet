package ua.restaurant.srvlt.controller.command.commands;

import ua.restaurant.srvlt.controller.command.*;

public enum Commands {
    ADMIN(new AdminPageCommand()),
    CLIENT( new ClientPageCommand()),
    DENIED(new DeniedCommand()),
    LOGIN_PAGE(new LoginPageCommand()),
    LOGIN(new LoginCommand()),
    LOGOUT(new LogoutCommand()),
    REGISTRATION(new RegistrationPageCommand()),
    REGISTER(new RegisterCommand()),
    CLIENT_ORDER( new ClientOrderCommand()),
    CLIENT_ORDERS( new ClientOrdersPageCommand());

    private Command command;

    Commands(Command command) {
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
