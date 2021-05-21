package app;


import DAO.impl.AccountTypeDAOimpl;
import model.AccountType;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AccountTypeController {
    static AccountTypeDAOimpl accounttype_dao = new AccountTypeDAOimpl();
    @GetMapping("/accounttypes")
    @ResponseBody
    public ArrayList<AccountType> accountTypes() {
        AccountTypeDAOimpl accountType_dao = new AccountTypeDAOimpl();
        return accountType_dao.getAllAccountTypes();
    }

    @GetMapping("/accounttypes/{accountTypeId}")
    public String department(@PathVariable String accountTypeId, Model model) {
        model.addAttribute("TypeId", accountTypeId);
        AccountType accountType = accounttype_dao.getAccountTypeById(Long.parseLong(accountTypeId));
        model.addAttribute("TypeName", accountType.getTypeName());
        model.addAttribute("AdditionalInfo", accountType.getAdditionalInfo());
        model.addAttribute("MaxCredit", accountType.getMaxCredit());
        model.addAttribute("Profitability", accountType.getProfitability());
        return "account_type";
    }
}
