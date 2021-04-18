package DAO;

import model.AccountType;
import model.Transaction;

import java.sql.SQLException;
import java.util.Collection;

public interface TransactionDAO {
    void addTransaction(Transaction transaction) throws SQLException;
    void updateTransaction(String transaction_id, Transaction transaction) throws SQLException;
    Transaction getTransactionById(String transaction_id) throws SQLException;
    Collection getAllTransactions() throws SQLException;
    void deleteTransaction(String transaction_id) throws SQLException;
}
