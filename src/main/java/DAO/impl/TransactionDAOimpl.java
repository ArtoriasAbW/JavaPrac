package DAO.impl;

import DAO.TransactionDAO;
import factory.MyFactory;
import model.Transaction;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class TransactionDAOimpl implements TransactionDAO {

    @Override
    public void addTransaction(Transaction transaction) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(transaction);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateTransaction(String transaction_id, Transaction transaction) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Transaction old_transaction = getTransactionById(transaction_id);
        session.evict(old_transaction);
        old_transaction.setTransactionDate(transaction.getTransactionDate());
        old_transaction.setAmount(transaction.getAmount());
        old_transaction.setReceiver(transaction.getReceiver());
        old_transaction.setSender(transaction.getSender());
        session.update(old_transaction);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Transaction getTransactionById(String transaction_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Transaction transaction = session.get(Transaction.class, transaction_id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return transaction;
    }

    @Override
    public ArrayList<Transaction> getAllTransactions() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select t from Transaction t");
        ArrayList<Transaction> transactions = (ArrayList<Transaction>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return transactions;
    }

    @Override
    public void deleteTransaction(String transaction_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Transaction transaction = getTransactionById(transaction_id);
        session.delete(transaction);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }
}
