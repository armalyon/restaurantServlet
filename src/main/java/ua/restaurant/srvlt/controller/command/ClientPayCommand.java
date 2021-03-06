package ua.restaurant.srvlt.controller.command;

import org.apache.log4j.Logger;
import ua.restaurant.srvlt.controller.command.utility.CommandUtility;
import ua.restaurant.srvlt.exception.IdNotFoundExeption;
import ua.restaurant.srvlt.exception.NotEnoughFundsException;
import ua.restaurant.srvlt.exception.TransactionException;
import ua.restaurant.srvlt.exception.UserNotFoundException;
import ua.restaurant.srvlt.service.PayBillService;

import javax.servlet.http.HttpServletRequest;

import static ua.restaurant.srvlt.constant.StringConstants.*;

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
            return CLIENT_BILLS_ERROR_FUNDS_REDIRECT;
        } catch (TransactionException e) {
            return CLIENT_BILLS_ERROR_REDIRECT;
        } catch (UserNotFoundException e) {
            LOGGER.error(e.getMessage());
            CommandUtility.removeUserFromSessionAndContext(request);
            return INTERNAL_ERROR_PAGE;
        } catch (IdNotFoundExeption idNotFoundExeption) {
            idNotFoundExeption.printStackTrace();
        }
        return CLIENT_BILLS_REDIRECT;
    }
}
