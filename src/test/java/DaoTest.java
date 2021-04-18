import DAO.impl.*;
import model.*;
import org.testng.annotations.Test;
import org.testng.Assert;

import javax.swing.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;


public class DaoTest {

    @Test
    public void DepartmentTest() {
        // add
        DepartmentDAOimpl obj = new DepartmentDAOimpl();
        Department dep = new Department("Южный", "ул.Мира д.9", "+75435479345");
        obj.addDepartment(dep);
        ArrayList<Department> deps = obj.getAllDepartments();
        int size = deps.size();
        Department last_dep = obj.getDepartmentById(dep.getDepartmentId());
        Assert.assertEquals(last_dep.getDepartmentAddress(), dep.getDepartmentAddress());
        Assert.assertEquals(last_dep.getDepartmentName(), dep.getDepartmentName());
        Assert.assertEquals(last_dep.getDepartmentsPhoneNumber(), dep.getDepartmentsPhoneNumber());
        dep.setDepartmentName("Северный");

        // update
        long last_id = last_dep.getDepartmentId();
        obj.updateDepartment(last_dep.getDepartmentId(), dep);
        last_dep = obj.getDepartmentById(last_id);
        Assert.assertEquals(last_dep.getDepartmentAddress(), dep.getDepartmentAddress());
        Assert.assertEquals(last_dep.getDepartmentName(), dep.getDepartmentName());
        Assert.assertEquals(last_dep.getDepartmentsPhoneNumber(), dep.getDepartmentsPhoneNumber());

        // delete
        obj.deleteDepartment(last_id);

        int new_size = obj.getAllDepartments().size();
        Assert.assertEquals(new_size + 1, size);
    }

    @Test
    public void ClientTest() {
        // add
        ClientDAOimpl obj = new ClientDAOimpl();
        Client client = new Client("Голубин Глеб Геннадьевич", "+79433123332",
                "dsdaad@ya.ru", "г.Москва Ленинградский проспект д.80 к.17", new Date(), "частное лицо");
        obj.addClient(client);

        // get all and by Id
        ArrayList<Client> clients = obj.getAllClients();
        Client lastClient = obj.getClientById(client.getClientId());
        Assert.assertEquals(lastClient.getClientName(), client.getClientName());
        Assert.assertEquals(lastClient.getPhoneNumber(), client.getPhoneNumber());
        Assert.assertEquals(lastClient.getClientAddress(), client.getClientAddress());
        Assert.assertEquals(lastClient.getEmail(), client.getEmail());
        Assert.assertEquals(lastClient.getFormattedRegistrationDate(), client.getFormattedRegistrationDate());
        Assert.assertEquals(lastClient.getClientType(), client.getClientType());

        int old_size = clients.size();

        // update
        client.setEmail("hoarahp@gmail.com");
        client.setClientName("Иванов Иван Иваныч");
        obj.updateClient(lastClient.getClientId(), client);

        lastClient = obj.getClientById(client.getClientId());
        Assert.assertEquals(lastClient.getClientName(), client.getClientName());
        Assert.assertEquals(lastClient.getPhoneNumber(), client.getPhoneNumber());
        Assert.assertEquals(lastClient.getClientAddress(), client.getClientAddress());
        Assert.assertEquals(lastClient.getEmail(), client.getEmail());
        Assert.assertEquals(lastClient.getFormattedRegistrationDate(), client.getFormattedRegistrationDate());
        Assert.assertEquals(lastClient.getClientType(), client.getClientType());

        // delete
        obj.deleteClient(client.getClientId());
        int new_size = obj.getAllClients().size();
        Assert.assertEquals(new_size + 1, old_size);
    }

    @Test
    public void AccountTest() throws ParseException {

        // add
        AccountDAOimpl obj = new AccountDAOimpl();
        ClientDAOimpl c_obj = new ClientDAOimpl();
        DepartmentDAOimpl d_obj = new DepartmentDAOimpl();
        AccountTypeDAOimpl at_obj = new AccountTypeDAOimpl();
        AccountType at = at_obj.getAccountTypeById((long) 1);
        Department dep = d_obj.getDepartmentById((long) 2);
        Client cl = c_obj.getClientById((long) 2);
        if (cl == null || dep == null || at == null) {
            System.out.println("need AccountType with id=1, Department with id=2, Client with id=2");
            return;
        }
        Account ac = new Account("646745644324", "активный", cl, 0, dep, at, new Date());
        obj.addAccount(ac);

        // get all and by number
        ArrayList<Account> accounts = obj.getAllAccounts();
        Account last_ac = obj.getAccountByNumber(ac.getAccountNumber());
        Assert.assertEquals(ac.getAccountNumber(), last_ac.getAccountNumber());
        Assert.assertEquals(ac.getAccountBalance(), last_ac.getAccountBalance());
        Assert.assertEquals(ac.getAccountStatus(), last_ac.getAccountStatus());
        Assert.assertEquals(ac.getClient().getClientId(), last_ac.getClient().getClientId());
        Assert.assertEquals(ac.getDepartment().getDepartmentId(), last_ac.getDepartment().getDepartmentId());
        Assert.assertEquals(ac.getFormattedOpeningDate(), last_ac.getFormattedOpeningDate());
        Assert.assertEquals(obj.getClientByAccountNumber(last_ac.getAccountNumber()).getClientId(), cl.getClientId());

        // update
        int old_quantity = accounts.size();
        ac.setAccountStatus("заморожен");
        obj.UpdateAccount(last_ac.getAccountNumber(), ac);
        accounts = obj.getAllAccounts();
        last_ac = obj.getAccountByNumber(ac.getAccountNumber());

        Assert.assertEquals(ac.getAccountNumber(), last_ac.getAccountNumber());
        Assert.assertEquals(ac.getAccountBalance(), last_ac.getAccountBalance());
        Assert.assertEquals(ac.getAccountStatus(), last_ac.getAccountStatus());
        Assert.assertEquals(ac.getClient().getClientId(), last_ac.getClient().getClientId());
        Assert.assertEquals(ac.getDepartment().getDepartmentId(), last_ac.getDepartment().getDepartmentId());
        Assert.assertEquals(ac.getFormattedOpeningDate(), last_ac.getFormattedOpeningDate());
        Assert.assertEquals(obj.getClientByAccountNumber(last_ac.getAccountNumber()).getClientId(), cl.getClientId());

        // delete
        obj.deleteAccount(last_ac.getAccountNumber());
        int new_quantity = obj.getAllAccounts().size();
        Assert.assertEquals(new_quantity + 1, old_quantity);
    }

    @Test
    public void TransactionTest() {

        // add
        TransactionDAOimpl obj = new TransactionDAOimpl();
        AccountDAOimpl a_obj = new AccountDAOimpl();
        ArrayList<Account> accounts = a_obj.getAllAccounts();
        if (accounts.size() < 3) {
            System.out.println("need 3 account in the accounts table");
            return;
        }
        Transaction transaction = new Transaction("532455886585754", new Date(), accounts.get(0), accounts.get(2), 803);
        obj.addTransaction(transaction);

        // get all and by Id
        ArrayList<Transaction> transactions = obj.getAllTransactions();
        Transaction last_transaction = obj.getTransactionById(transaction.getTransactionId());
        Assert.assertEquals(transaction.getFormattedTransactionDate(), last_transaction.getFormattedTransactionDate());
        Assert.assertEquals(transaction.getTransactionId(), last_transaction.getTransactionId());
        Assert.assertEquals(transaction.getReceiver().getAccountNumber(), last_transaction.getReceiver().getAccountNumber());
        Assert.assertEquals(transaction.getSender().getAccountNumber(), last_transaction.getSender().getAccountNumber());
        Assert.assertEquals(transaction.getAmount(), last_transaction.getAmount());

        int old_size = transactions.size();

        // update
        transaction.setAmount(4134);
        obj.updateTransaction(last_transaction.getTransactionId(), transaction);

        last_transaction = obj.getTransactionById(transaction.getTransactionId());

        Assert.assertEquals(transaction.getFormattedTransactionDate(), last_transaction.getFormattedTransactionDate());
        Assert.assertEquals(transaction.getTransactionId(), last_transaction.getTransactionId());
        Assert.assertEquals(transaction.getReceiver().getAccountNumber(), last_transaction.getReceiver().getAccountNumber());
        Assert.assertEquals(transaction.getSender().getAccountNumber(), last_transaction.getSender().getAccountNumber());
        Assert.assertEquals(transaction.getAmount(), last_transaction.getAmount());

        // delete
        obj.deleteTransaction(last_transaction.getTransactionId());
        transactions = obj.getAllTransactions();
        int new_size = transactions.size();
        Assert.assertEquals(new_size + 1, old_size);
    }


    @Test
    public void AccountTypeTest() {
        // add
        AccountTypeDAOimpl obj = new AccountTypeDAOimpl();
        AccountType accountType = new AccountType("Премиум", 5000000, 9, "Обслуживание 5000 в месяц");
        obj.AddAccountType(accountType);

        // get all and by Id
        ArrayList<AccountType> accountTypes = obj.getAllAccountTypes();
        AccountType lastAccountType = obj.getAccountTypeById(accountType.getTypeId());
        Assert.assertEquals(lastAccountType.getTypeName(), accountType.getTypeName());
        Assert.assertEquals(lastAccountType.getAdditionalInfo(), accountType.getAdditionalInfo());
        Assert.assertEquals(lastAccountType.getMaxCredit(), accountType.getMaxCredit());
        Assert.assertEquals(lastAccountType.getProfitability(), accountType.getProfitability());

        // update
        accountType.setAdditionalInfo("Обслуживание 10000 в месяц");
        obj.UpdateAccountType(lastAccountType.getTypeId(), accountType);

        accountTypes = obj.getAllAccountTypes();
        lastAccountType = obj.getAccountTypeById(accountType.getTypeId());

        int old_size = accountTypes.size();
        Assert.assertEquals(lastAccountType.getTypeName(), accountType.getTypeName());
        Assert.assertEquals(lastAccountType.getAdditionalInfo(), accountType.getAdditionalInfo());
        Assert.assertEquals(lastAccountType.getMaxCredit(), accountType.getMaxCredit());
        Assert.assertEquals(lastAccountType.getProfitability(), accountType.getProfitability());

        // delete
        obj.deleteAccountType(accountType.getTypeId());
        int new_size = obj.getAllAccountTypes().size();
        Assert.assertEquals(new_size + 1, old_size);
    }

}
