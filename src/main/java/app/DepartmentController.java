package app;

import DAO.impl.DepartmentDAOimpl;
import model.Department;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class DepartmentController {

    @GetMapping("/departments/{departmentId}")
    public String greetings(@PathVariable String departmentId, Model model) {
        model.addAttribute("DepartmentId", departmentId);
        DepartmentDAOimpl department_dao = new DepartmentDAOimpl();
        Department department = department_dao.getDepartmentById(Long.parseLong(departmentId));
        model.addAttribute("DepartmentName", department.getDepartmentName());
        model.addAttribute("DepartmentAddress", department.getDepartmentAddress());
        model.addAttribute("DepartmentPhoneNumber", department.getDepartmentsPhoneNumber());
        return "department";
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        DepartmentDAOimpl department_dao = new DepartmentDAOimpl();
        model.addAttribute("departments", department_dao.getAllDepartments());
        return "list";
    }

    @GetMapping("/departments/form")
    public String add_department() {
        return "form";
    }

    @PostMapping("/departments")
    public String addDepartment(@RequestParam Map<String, String> body) {
        String name = body.get("departmentName");
        String address = body.get("departmentAddress");
        String phoneNumber = body.get("departmentPhoneNumber");
        Department department = new Department(name, address, phoneNumber);
        new DepartmentDAOimpl().addDepartment(department);
        return "redirect:/departments";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        new DepartmentDAOimpl().deleteDepartment(id);
        return "redirect:/departments";
    }
}
