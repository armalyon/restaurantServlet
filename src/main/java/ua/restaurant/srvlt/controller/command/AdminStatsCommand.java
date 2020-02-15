package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.dto.UserInfoDTO;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.pagination.Page;
import ua.restaurant.srvlt.model.service.ClientBillsService;
import ua.restaurant.srvlt.model.service.UserInfoDTOService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class AdminStatsCommand implements Command {
    private static final Logger LOGGER = Logger.getLogger(AdminStatsCommand.class);
    private static final int PAGE_SIZE = 5;
    private UserInfoDTOService infoDTOService = new UserInfoDTOService();
    private ClientBillsService clientBillsService = new ClientBillsService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME_ATTRIBUTE);
        int pageNumber = CommandUtility.getPageNumber(request);
        UserInfoDTO userInfoDTO = null;
        try {
            userInfoDTO = infoDTOService.getUserInfDTOByUSername(username);
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
        }
        Page<Bill> page = clientBillsService.getBillsByUsername(username, pageNumber, PAGE_SIZE);
        LOGGER.debug(userInfoDTO);
        LOGGER.debug(page);
        request.setAttribute(USER_INFO_DTO_ATTRIBUTE, userInfoDTO);
        request.setAttribute(PAGE_ATTRIBUTE, page);
        return ADMIN_STATS_PAGE;
    }
}
