package DAO.impl;

import DAO.ClientDAO;
import factory.MyFactory;
import model.Account;
import model.Client;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.Collection;

public class ClientDAOimpl implements ClientDAO {

    public ClientDAOimpl() {

    }

    @Override
    public void addClient(Client client) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(client);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }

    }

    @Override
    public void updateClient(Long client_id, Client client) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Client old_client = getClientById(client_id);
        session.evict(old_client);
        old_client.setClientName(client.getClientName());
        old_client.setClientAddress(client.getClientAddress());
        old_client.setClientType(client.getClientType());
        old_client.setEmail(client.getEmail());
        old_client.setPhoneNumber(client.getPhoneNumber());
        old_client.setRegistrationDate(client.getRegistrationDate());
        session.update(old_client);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Client getClientById(Long client_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Client client = session.get(Client.class, client_id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return client;
    }

    @Override
    public ArrayList<Client> getAllClients() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select c from Client c");
        ArrayList<Client> clients = (ArrayList<Client>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return clients;
    }

    @Override
    public void deleteClient(Long client_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Client client = getClientById(client_id);
        session.delete(client);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<Account> getAllClientAccounts(Long client_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Client cur_client = getClientById(client_id);
        Query query = session.createQuery("FROM Account A WHERE A.Client = :cur_client");
        query.setParameter("cur_client", cur_client);
        ArrayList<Account> accounts = (ArrayList<Account>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return accounts;
    }
}
