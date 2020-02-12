package ua.restaurant.srvlt.controller.command;

import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.model.dto.UserInfoDTO;
import ua.restaurant.srvlt.model.entity.Bill;
import ua.restaurant.srvlt.model.pagination.Page;
import ua.restaurant.srvlt.model.service.ClientBillsService;
import ua.restaurant.srvlt.model.service.UserInfoDTOService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constants.StringConstants.*;

public class AdminStatsCommand implements Command {
    private static final int PAGE_SIZE = 5;
    private UserInfoDTOService infoDTOService = new UserInfoDTOService();
    private ClientBillsService clientBillsService = new ClientBillsService();

    @Override
    public String execute(HttpServletRequest request) {
        String username = request.getParameter(USERNAME_ATTRIBUTE);
        int pageNumber = CommandUtility.getPageNumber(request);
        UserInfoDTO userInfoDTO = infoDTOService.getUserInfDTOByUSername(username);
        Page<Bill> page = clientBillsService.getBillsByUsername(username, pageNumber, PAGE_SIZE);
        request.setAttribute(USER_INFO_DTO_ATTRIBUTE, userInfoDTO);
        request.setAttribute(PAGE_ATTRIBUTE, page);
        return ADMIN_STATS_PAGE;
    }
}
