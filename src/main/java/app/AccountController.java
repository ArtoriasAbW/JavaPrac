package app;

import DAO.impl.*;
import com.sun.xml.bind.v2.runtime.BinderImpl;
import model.Account;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;
import java.util.function.BinaryOperator;

@Controller
public class AccountController {
    static AccountDAOimpl account_dao = new AccountDAOimpl();
    @GetMapping("/accounts")
    public String accounts(
            @RequestParam(name = "clientid", required = false) String clientId,
            @RequestParam(name = "departmentid", required = false) String departmentId,
            Model model) {
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
        Account account = account_dao.getAccountByNumber(accountNumber);
        model.addAttribute("accountBalance", account.getAccountBalance());
        model.addAttribute("accountStatus", account.getAccountStatus());
        model.addAttribute("Client", account.getClient());
        model.addAttribute("Department", account.getDepartment());
        model.addAttribute("openingDate", account.getFormattedOpeningDate());
        return "account";
    }

    @GetMapping("/accounts/form")
    public String accountForm(Model model) {
        model.addAttribute("account", new Account());
        model.addAttribute("alldepartments", new DepartmentDAOimpl().getAllDepartments());
        model.addAttribute("allclients", new ClientDAOimpl().getAllClients());
        model.addAttribute("alltypes", new AccountTypeDAOimpl().getAllAccountTypes());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/accounts");
        return "account_form";
    }

    @PostMapping("/accounts")
    public String addAccount(@Valid Account account, BindingResult bindingResult, Model model) {
        Long department_id = account.getDepartment().getDepartmentId();
        Long client_id = account.getClient().getClientId();
        Long type_id = account.getType().getTypeId();
        System.out.println(department_id);
        System.out.println(client_id);
        System.out.println(type_id);
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            model.addAttribute("action", "/accounts");
            model.addAttribute("alldepartments", new DepartmentDAOimpl().getAllDepartments());
            model.addAttribute("allclients", new ClientDAOimpl().getAllClients());
            model.addAttribute("alltypes", new AccountTypeDAOimpl().getAllAccountTypes());
            return "account_form";
        }
        account.setDepartment(new DepartmentDAOimpl().getDepartmentById(department_id));
        account.setClient(new ClientDAOimpl().getClientById(client_id));
        account.setType(new AccountTypeDAOimpl().getAccountTypeById(type_id));
        account_dao.addAccount(account);
        return "redirect:/accounts";
    }

    @PostMapping("accounts/delete")
    public String deleteAccount(@RequestParam String accountNumber) {
        account_dao.deleteAccount(accountNumber);
        return "redirect:/accounts";
    }

}
