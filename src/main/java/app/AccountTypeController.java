package app;


import DAO.impl.AccountTypeDAOimpl;
import model.AccountType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class AccountTypeController {
    static AccountTypeDAOimpl accounttype_dao = new AccountTypeDAOimpl();

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
