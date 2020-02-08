package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.constants.TextConstants;
import ua.restaurant.srvlt.model.service.AdminBillService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.*;
import static ua.restaurant.srvlt.constants.TextConstants.ADMIN_CONFIRMED_REDIRECT;

public class AdminBillCommand implements Command {
    private AdminBillService adminBillService = new AdminBillService();

    @Override
    public String execute(HttpServletRequest request) {

        long billId = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        adminBillService.saveNewBill(billId);
        return ADMIN_CONFIRMED_REDIRECT;
    }
}
