package app;

import DAO.impl.AccountDAOimpl;
import DAO.impl.AccountTypeDAOimpl;
import DAO.impl.TransactionDAOimpl;
import model.Account;
import model.AccountType;
import model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AccountController {
    @GetMapping("/accounts")
    @ResponseBody
    public ArrayList<Account> accounts() {
        AccountDAOimpl account_dao = new AccountDAOimpl();
        return account_dao.getAllAccounts();
    }

}
