package DAO.impl;

import DAO.AccountTypeDAO;
import factory.MyFactory;
import model.Account;
import model.AccountType;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class AccountTypeDAOimpl implements AccountTypeDAO {

    @Override
    public void AddAccountType(AccountType accountType) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(accountType);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void UpdateAccountType(Long accountTypeId, AccountType accountType) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        AccountType oldAccountType = getAccountTypeById(accountTypeId);
        session.evict(oldAccountType);
        oldAccountType.setTypeName(accountType.getTypeName());
        oldAccountType.setMaxCredit(accountType.getMaxCredit());
        oldAccountType.setProfitability(accountType.getProfitability());
        oldAccountType.setAdditionalInfo(accountType.getAdditionalInfo());
        session.update(oldAccountType);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public AccountType getAccountTypeById(Long accountTypeId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        AccountType accountType = session.get(AccountType.class, accountTypeId);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return accountType;
    }

    @Override
    public ArrayList<AccountType> getAllAccountTypes() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select at from AccountType at");
        ArrayList<AccountType> accountTypes = (ArrayList<AccountType>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return accountTypes;
    }

    @Override
    public void deleteAccountType(Long accountTypeId) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        AccountType accountType = getAccountTypeById(accountTypeId);
        session.delete(accountType);
        session.getTransaction().commit();
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
