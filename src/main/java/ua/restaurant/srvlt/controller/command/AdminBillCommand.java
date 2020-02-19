package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.model.service.AdminBillService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.*;

public class AdminBillCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminBillCommand.class);
    private AdminBillService adminBillService = new AdminBillService();

    @Override
    public String execute(HttpServletRequest request) {

        long billId = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        try {
            adminBillService.saveNewBill(billId);
        } catch (IdNotFoundExeption e) {
            LOGGER.error(e.getMessage());
            return ADMIN_CONFIRMED_REDIRECT_ERROR;
        }
        return ADMIN_CONFIRMED_REDIRECT;
    }
}
