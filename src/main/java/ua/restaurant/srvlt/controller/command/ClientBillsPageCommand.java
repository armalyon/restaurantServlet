package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.pagination.Page;
import ua.restaurant.srvlt.model.service.ClientBillsService;
import ua.restaurant.srvlt.model.service.UserService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.TextConstants.*;

public class ClientBillsPageCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(ClientBillsPageCommand.class);
    private static final int PAGE_SIZE = 5;

    private ClientBillsService clientBillsService = new ClientBillsService();
    private UserService userService = new UserService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute(USERNAME_ATTRIBUTE);
        int pageNumber = CommandUtility.getPageNumber(request);

        Page<Bill> page = clientBillsService.getBillsByUsername(username, pageNumber, PAGE_SIZE );
        long funds = userService.getFundsByUsername(username);

        LOGGER.debug(page.getRecords().get(0).getPaymentDateTime());

        request.setAttribute(FUNDS_ATTRIBUTE, funds);
        request.setAttribute(PAGE_ATTRIBUTE, page);

        return CLIENT_BILLS_PAGE;
    }
}
