package app;

import DAO.impl.ClientDAOimpl;
import model.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class ClientController {

    @GetMapping("/clients")
    @ResponseBody
    public ArrayList<Client> clients() {
        ClientDAOimpl client_dao = new ClientDAOimpl();
        return client_dao.getAllClients();
    }
}
