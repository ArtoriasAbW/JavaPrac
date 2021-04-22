package app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import DAO.impl.*;
import model.*;

import java.util.ArrayList;
import java.util.Map;

@Controller
public class MainController {

    @GetMapping("/main")
    public String index() {
        return "main";
    }
}
