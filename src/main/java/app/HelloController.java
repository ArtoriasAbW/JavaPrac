package app;

import model.Account;
import model.Client;
import model.Department;
import model.Transaction;
import org.springframework.web.bind.annotation.*;

import DAO.impl.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() { return "Hello, creature!"; }

    @GetMapping("/api/departments")
    @ResponseBody
    public ArrayList<Department> departments() {
        DepartmentDAOimpl department_dao = new DepartmentDAOimpl();
        return department_dao.getAllDepartments();
    }

    @GetMapping("/api/clients")
    @ResponseBody
    public ArrayList<Client> clients() {
        ClientDAOimpl client_dao = new ClientDAOimpl();
        return client_dao.getAllClients();
    }

    @GetMapping("/api/accounts")
    @ResponseBody
    public ArrayList<Account> accounts() {
        AccountDAOimpl account_dao = new AccountDAOimpl();
        return account_dao.getAllAccounts();
    }

    @GetMapping("/api/transactions")
    @ResponseBody
    public ArrayList<Transaction> transactions() {
        TransactionDAOimpl transaction_dao = new TransactionDAOimpl();
        return transaction_dao.getAllTransactions();
    }

    @GetMapping("/api/clients/{id}")
    @ResponseBody
    public Client getClient(@PathVariable String id) {
        ClientDAOimpl client_dao = new ClientDAOimpl();
        return client_dao.getClientById((long) Integer.parseInt(id));
    }

    @GetMapping("/api/accounts/{account_number}")
    @ResponseBody
    public Account getAccount(@PathVariable String account_number) {
        AccountDAOimpl account_dao = new AccountDAOimpl();
        return account_dao.getAccountByNumber(account_number);
    }
}
