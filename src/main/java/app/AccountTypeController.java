package app;


import DAO.impl.AccountTypeDAOimpl;
import model.AccountType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class AccountTypeController {
    @GetMapping("/accounttypes")
    @ResponseBody
    public ArrayList<AccountType> accountTypes() {
        AccountTypeDAOimpl accountType_dao = new AccountTypeDAOimpl();
        return accountType_dao.getAllAccountTypes();
    }
}
