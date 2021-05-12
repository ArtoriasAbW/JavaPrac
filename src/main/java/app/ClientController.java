package app;

import DAO.impl.ClientDAOimpl;
import DAO.impl.DepartmentDAOimpl;
import model.Client;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;


@Controller
public class ClientController {

    @GetMapping("/clients")
    public String clients(Model model) {
        ClientDAOimpl client_dao = new ClientDAOimpl();
        model.addAttribute("clients", client_dao.getAllClients());
        return "client_list";
    }

    @GetMapping("/clients/{clientId}")
    public String client(@PathVariable String clientId, Model model) {
        ClientDAOimpl client_dao = new ClientDAOimpl();
        Client client = client_dao.getClientById(Long.parseLong(clientId));
        model.addAttribute("clientId", clientId);
        model.addAttribute("clientName", client.getClientName());
        model.addAttribute("clientAddress", client.getClientAddress());
        model.addAttribute("clientType", client.getClientType());
        model.addAttribute("clientPhoneNumber", client.getPhoneNumber());
        model.addAttribute("clientEmail", client.getEmail());
        model.addAttribute("clientRegistrationDate", client.getFormattedRegistrationDate());
        return "client";
    }

    @GetMapping("clients/form")
    public String clientForm() {
        return "client_form";
    }

    @PostMapping("/clients")
    public String addClient(@RequestParam Map<String, String> body) throws ParseException {
        String name = body.get("clientName");
        String address = body.get("clientAddress");
        String type = body.get("clientType");
        String phoneNumber = body.get("clientPhoneNumber");
        String email = body.get("clientEmail");
        String registrationDate = body.get("clientRegistrationDate");
        Date date = new SimpleDateFormat("yyyy/MM/dd").parse(registrationDate);
        Client client = new Client(name, phoneNumber, email, address, date, type);
        new ClientDAOimpl().addClient(client);
        return "redirect:/clients";
    }

    @PostMapping("/clients/delete")
    public String deleteClient(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        new ClientDAOimpl().deleteClient(id);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientId}/update")
    public String updateClientForm(@PathVariable String clientId, Model model) {
        Client client = new ClientDAOimpl().getClientById(Long.parseLong(clientId));
        model.addAttribute("id", client.getClientId());
        model.addAttribute("name", client.getClientName());
        model.addAttribute("address", client.getClientAddress());
        model.addAttribute("type", client.getClientType());
        model.addAttribute("email", client.getEmail());
        model.addAttribute("date", client.getFormattedRegistrationDate());
        model.addAttribute("phone_number", client.getPhoneNumber());
        return "client_update";
    }

    @PostMapping("/clients/{clientId}")
    public String updateClient(@PathVariable String clientId, @RequestParam Map<String, String> body) {
        return "redirect:/clients/{clientId}";
    }
}
