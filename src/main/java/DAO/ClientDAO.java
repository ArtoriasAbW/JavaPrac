package DAO;

import model.Client;

import java.sql.SQLException;
import java.util.Collection;

public interface ClientDAO {
    void addClient(Client client) throws SQLException;
    void updateClient(Long client_id, Client client) throws  SQLException;
    Client getClientById(Long client_id) throws SQLException;
    Collection getAllClients() throws SQLException;
    void deleteClient(Long client_id) throws SQLException;
    Collection getAllClientAccounts(Long client_id) throws SQLException;
}
