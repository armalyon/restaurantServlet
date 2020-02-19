package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.dto.pagination.Page;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.service.ClientBillsService;
import ua.restaurant.srvlt.model.service.UserFundsService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.*;

public class ClientBillsPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientBillsPageCommand.class);
    private static final int PAGE_SIZE = 5;

    private ClientBillsService clientBillsService = new ClientBillsService();
    private UserFundsService userFundsService = new UserFundsService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute(USERNAME_ATTRIBUTE);
        int pageNumber = CommandUtility.getPageNumber(request);

        Page<Bill> page = clientBillsService.getBillsByUsername(username, pageNumber, PAGE_SIZE);
        long funds = 0;
        try {
            funds = userFundsService.getFundsByUsername(username);
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        request.setAttribute(FUNDS_ATTRIBUTE, funds);
        request.setAttribute(PAGE_ATTRIBUTE, page);

        return CLIENT_BILLS_PAGE;
    }
}
