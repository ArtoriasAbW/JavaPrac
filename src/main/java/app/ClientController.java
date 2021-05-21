package app;

import DAO.impl.ClientDAOimpl;
import DAO.impl.DepartmentDAOimpl;
import model.Client;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;


@Controller
public class ClientController {
    static ClientDAOimpl client_dao = new ClientDAOimpl();

    @GetMapping("/clients")
    public String clients(Model model) {
        model.addAttribute("clients", client_dao.getAllClients());
        return "client_list";
    }

    @GetMapping("/clients/{clientId}")
    public String client(@PathVariable String clientId, Model model) {
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
    public String clientForm(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/clients");
        return "client_form";
    }

    @PostMapping("/clients")
    public String addClient(@Valid Client client, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            model.addAttribute("action", "/clients");
            return "client_form";
        }
        client_dao.addClient(client);
        return "redirect:/clients";
    }

    @PostMapping("/clients/delete")
    public String deleteClient(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        client_dao.deleteClient(id);
        return "redirect:/clients";
    }

    @GetMapping("/clients/{clientId}/update")
    public String updateClientForm(@PathVariable String clientId, Model model) {
        model.addAttribute("client", client_dao.getClientById(Long.parseLong(clientId)));
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/clients/" + clientId);
        return "client_form";
    }


    @PostMapping("/clients/{clientId}")
    public String updateClient(@Valid Client client,
                                   BindingResult bindingResult,
                                   Model model,
                                   @PathVariable String clientId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/clients/" + clientId);
            return "client_form";
        }
        client_dao.updateClient(Long.parseLong(clientId), client);
        return "redirect:/clients/{clientId}";
    }
}
