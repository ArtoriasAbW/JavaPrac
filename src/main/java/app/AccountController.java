package app;

import DAO.impl.*;
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
        model.addAttribute("AccountType", account.getType());
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

    @PostMapping("/accounts/delete")
    public String deleteAccount(@RequestParam Map<String, String> body) {
        account_dao.deleteAccount(body.get("id"));
        return "redirect:/accounts";
    }

    @GetMapping("/accounts/{accountNumber}/update")
    public String updateAccountForm(@PathVariable String accountNumber, Model model) {
        model.addAttribute("account", account_dao.getAccountByNumber(accountNumber));
        model.addAttribute("alldepartments", new DepartmentDAOimpl().getAllDepartments());
        model.addAttribute("allclients", new ClientDAOimpl().getAllClients());
        model.addAttribute("alltypes", new AccountTypeDAOimpl().getAllAccountTypes());
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/accounts/" + accountNumber);
        return "account_form";
    }

    @PostMapping("accounts/{accountNumber}")
    public String updateAccount(@Valid Account account,
                                BindingResult bindingResult,
                                Model model,
                                @PathVariable String accountNumber) {
        Long department_id = account.getDepartment().getDepartmentId();
        Long client_id = account.getClient().getClientId();
        Long type_id = account.getType().getTypeId();
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/accounts/" + accountNumber);
            model.addAttribute("alldepartments", new DepartmentDAOimpl().getAllDepartments());
            model.addAttribute("allclients", new ClientDAOimpl().getAllClients());
            model.addAttribute("alltypes", new AccountTypeDAOimpl().getAllAccountTypes());
            return "account_form";
        }
        account.setDepartment(new DepartmentDAOimpl().getDepartmentById(department_id));
        account.setClient(new ClientDAOimpl().getClientById(client_id));
        account.setType(new AccountTypeDAOimpl().getAccountTypeById(type_id));
        account_dao.UpdateAccount(accountNumber, account);
        return "redirect:/accounts/{accountNumber}";
    }

}
