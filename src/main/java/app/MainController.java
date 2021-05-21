package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DAO.impl.*;
import model.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/main")
    public String index() {
        return "main";
    }


    @GetMapping("test")
    public String test(Model model) {
        model.addAttribute("client", new Client());
        model.addAttribute("all_clients", new ClientDAOimpl().getAllClients());
        return "test";
    }

    @PostMapping("test")
    public String ads(@Valid Client client) {
        System.out.println(client.getClientId());
        return "redirect:/test";
    }
}
