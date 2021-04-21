package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DAO.impl.*;
import model.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/main")
    public String index() {
        return "main";
    }

    @GetMapping("/departments/{departmentId}")
    public String greetings(@PathVariable String departmentId, Model model) {
        model.addAttribute("DepartmentId", departmentId);
        DepartmentDAOimpl department_dao = new DepartmentDAOimpl();
        Department department = department_dao.getDepartmentById(Long.parseLong(departmentId));
        model.addAttribute("DepartmentName", department.getDepartmentName());
        model.addAttribute("DepartmentAddress", department.getDepartmentAddress());
        model.addAttribute("DepartmentPhoneNumber", department.getDepartmentsPhoneNumber());
        return "department";
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        DepartmentDAOimpl department_dao = new DepartmentDAOimpl();
        model.addAttribute("departments", department_dao.getAllDepartments());
        return "list";
    }

    @GetMapping("/departments/form")
    public String add_department() {
        return "form";
    }

    @PostMapping("/departments")
    public String addDepartment(@RequestParam Map<String, String> body, Model model) {
        System.out.println(body);
        String name = body.get("departmentName");
        String address = body.get("departmentAddress");
        String phoneNumber = body.get("departmentPhoneNumber");
        Department department = new Department(name, address, phoneNumber);
        new DepartmentDAOimpl().addDepartment(department);
        return "redirect:/departments";
    }

    @GetMapping("/clients")
    @ResponseBody
    public ArrayList<Client> clients() {
        ClientDAOimpl client_dao = new ClientDAOimpl();
        return client_dao.getAllClients();
    }

    @GetMapping("/accounts")
    @ResponseBody
    public ArrayList<Account> accounts() {
        AccountDAOimpl account_dao = new AccountDAOimpl();
        return account_dao.getAllAccounts();
    }

    @GetMapping("/transactions")
    @ResponseBody
    public ArrayList<Transaction> transactions() {
        TransactionDAOimpl transaction_dao = new TransactionDAOimpl();
        return transaction_dao.getAllTransactions();
    }

    @GetMapping("/accounttypes")
    @ResponseBody
    public ArrayList<AccountType> accountTypes() {
        AccountTypeDAOimpl accountType_dao = new AccountTypeDAOimpl();
        return accountType_dao.getAllAccountTypes();
    }
}
