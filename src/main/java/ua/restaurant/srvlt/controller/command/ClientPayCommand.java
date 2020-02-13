package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.NotEnoughFundsException;
import ua.restaurant.srvlt.model.service.PayBillService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class ClientPayCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientPayCommand.class);

    private PayBillService payBillService = new PayBillService();

    @Override
    public String execute(HttpServletRequest request) {
        long billId = Long.parseLong(request.getParameter(ID_ATTRIBUTE));
        String username = (String) request.getSession()
                .getAttribute(USERNAME_ATTRIBUTE);
        try {
            payBillService.payBill(billId, username);
        } catch (NotEnoughFundsException e) {
            LOGGER.warn(e.getMessage() + e.getBillId());
            //TODO handling
        }

        return CLIENT_BILLS_REDIRECT;
    }
}
