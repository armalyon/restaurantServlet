package ua.restaurant.srvlt.model.service;


import org.apache.log4j.Logger;
import ua.restaurant.srvlt.exceptions.IdNotFoundExeption;
import ua.restaurant.srvlt.exceptions.NotEnoughFundsException;
import ua.restaurant.srvlt.exceptions.TransactionException;
import ua.restaurant.srvlt.exceptions.UserNotFoundException;
import ua.restaurant.srvlt.model.dao.BillDao;
import ua.restaurant.srvlt.model.dao.DaoFactory;
import ua.restaurant.srvlt.model.dao.UserDao;
import ua.restaurant.srvlt.model.entity.Bill;

import java.time.LocalDateTime;

import static ua.restaurant.srvlt.constant.StringConstants.ADMIN_USERNAME;
import static ua.restaurant.srvlt.model.entity.type.BillStatement.PAYED;

public class PayBillService {
    private static final Logger LOGGER = Logger.getLogger(PayBillService.class);

    private BillDao billDao = DaoFactory.getInstance().createBillDao();
    private UserDao userDao = DaoFactory.getInstance().createUserDao();

    public boolean payBill(Long billId, String username)
            throws NotEnoughFundsException,
            TransactionException,
            UserNotFoundException,
            IdNotFoundExeption {
        Bill bill = getBillById(billId);
        String billUsername = bill.getOrder().getUser().getUsername();
        if (billUsername.equals(username)) {
            long transactionSum = bill.getOrder().getTotalPrice();
            if (isBillNotPayed(bill) && isFundsEnough(bill, username)) {
                return updateEntities(billId, username, transactionSum);
            }
        } else LOGGER.error("Violation by " + username);
        return false;
    }

    private boolean updateEntities(Long billId, String username, long transactionSum) throws TransactionException {
        userDao.transferFunds(username, ADMIN_USERNAME, transactionSum);
        billDao.updatePaymentDateStatementById(PAYED, LocalDateTime.now(), billId);
        return true;
    }


    private boolean isFundsEnough(Bill bill, String username) throws NotEnoughFundsException, UserNotFoundException {
        long invoice = bill.getOrder().getTotalPrice();
        long funds = getFunds(username);
        if (invoice > funds) throw new NotEnoughFundsException("not enough funds", invoice - funds, bill.getId());
        return true;
    }

    private long getFunds(String username) throws UserNotFoundException {
        return userDao.findUserByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("ERROR: username not found ", username))
                .getFunds();
    }

    private boolean isBillNotPayed(Bill bill) {
        return !bill.getStatement().equals(PAYED);
    }

    private Bill getBillById(long id) throws IdNotFoundExeption {
        return billDao.findById(id)
                .orElseThrow(() -> new IdNotFoundExeption(
                        PayBillService.class.getName(), id));
    }


}
