package DAO;

import model.Account;
import model.Client;
import model.Department;

import java.sql.SQLException;
import java.util.Collection;

public interface AccountDAO {
    void addAccount(Account account);
    void UpdateAccount(String account_number, Account account);
    Account getAccountByNumber(String AccountNumber);
    Collection getAllAccounts();
    void deleteAccount(String AccountNumber);
    Collection getHistory(String AccountNumber);
    Client getClientByAccountNumber(String AccountNumber);
}
