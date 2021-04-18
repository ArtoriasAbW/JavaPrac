package DAO;

import model.AccountType;

import java.sql.SQLException;
import java.util.Collection;

public interface AccountTypeDAO {
    void AddAccountType(AccountType accountType) throws SQLException;
    void UpdateAccountType(Long accountTypeId, AccountType accountType) throws SQLException;
    AccountType getAccountTypeById(Long accountTypeId) throws SQLException;
    Collection getAllAccountTypes() throws SQLException;
    void deleteAccountType(Long accountTypeId) throws SQLException;
}
