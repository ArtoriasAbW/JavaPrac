package DAO.impl;

import DAO.AccountDAO;
import factory.MyFactory;
import model.Account;
import model.Client;
import model.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AccountDAOimpl implements AccountDAO {

    @Override
    public void addAccount(Account account) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(account);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void UpdateAccount(String accountNumber, Account account) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Account old_account = getAccountByNumber(accountNumber);
        session.evict(old_account);
        old_account.setAccountBalance(account.getAccountBalance());
        old_account.setAccountStatus(account.getAccountStatus());
        old_account.setType(account.getType());
        old_account.setClient(account.getClient());
        old_account.setDepartment(account.getDepartment());
        old_account.setOpeningDate(account.getOpeningDate());
        session.update(old_account);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Account account = session.get(Account.class, accountNumber);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return account;
    }

    @Override
    public ArrayList<Account> getAllAccounts() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select a from Account a");
        ArrayList<Account> accounts = (ArrayList<Account>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return accounts;
    }

    @Override
    public void deleteAccount(String accountNumber) {
        Session session = null;
        session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Account account = getAccountByNumber(accountNumber);
        session.delete(account);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<Transaction> getHistory(String accountNumber) {
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Account curAccount = getAccountByNumber(accountNumber);
        Query query = session.createQuery(
                "FROM Transaction t WHERE t.Receiver = :curAccount OR t.Sender = :curAccount");
        query.setParameter("curAccount", curAccount);
        transactions = (ArrayList<Transaction>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return transactions;
    }

    @Override
    public Client getClientByAccountNumber(String accountNumber) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Account account = getAccountByNumber(accountNumber);
        Client client = account.getClient();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return client;
    }
}
