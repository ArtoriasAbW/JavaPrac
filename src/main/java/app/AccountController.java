package app;

import DAO.impl.*;
import model.Account;
import model.AccountType;
import model.Department;
import model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AccountController {

    @GetMapping("/accounts")
    public String accounts(
            @RequestParam(name = "clientid", required = false) String clientId,
            @RequestParam(name = "departmentid", required = false) String departmentId,
            Model model) {
        AccountDAOimpl account_dao = new AccountDAOimpl();
        if (clientId != null) {
            model.addAttribute("accounts", new ClientDAOimpl().getAllClientAccounts(Long.parseLong(clientId)));
        } else if (departmentId != null) {
            model.addAttribute("accounts", new DepartmentDAOimpl().getAllDepartmentAccounts(Long.parseLong(departmentId)));
        } else {
            model.addAttribute("accounts", account_dao.getAllAccounts());
        }
        return "account_list";
    }


    @GetMapping("/accounts/{accountNumber}")
    public String account(@PathVariable String accountNumber, Model model) {
        model.addAttribute("accountNumber", accountNumber);
        AccountDAOimpl account_dao = new AccountDAOimpl();
        Account account = account_dao.getAccountByNumber(accountNumber);
        model.addAttribute("accountBalance", account.getAccountBalance());
        model.addAttribute("accountStatus", account.getAccountStatus());
        model.addAttribute("Client", account.getClient());
        model.addAttribute("Department", account.getDepartment());
        model.addAttribute("openingDate", account.getFormattedOpeningDate());
        return "account";
    }

}
