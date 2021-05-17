package app;

import DAO.impl.DepartmentDAOimpl;
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
public class DepartmentController {
    static DepartmentDAOimpl department_dao = new DepartmentDAOimpl();

    @GetMapping("/departments/{departmentId}")
    public String department(@PathVariable String departmentId, Model model) {
        model.addAttribute("DepartmentId", departmentId);
        Department department = department_dao.getDepartmentById(Long.parseLong(departmentId));
        model.addAttribute("DepartmentName", department.getDepartmentName());
        model.addAttribute("DepartmentAddress", department.getDepartmentAddress());
        model.addAttribute("DepartmentPhoneNumber", department.getDepartmentPhoneNumber());
        return "department";
    }

    @GetMapping("/departments")
    public String departments(Model model) {
        model.addAttribute("departments", department_dao.getAllDepartments());
        return "department_list";
    }

    @GetMapping("/departments/form")
    public String addDepartmentForm(Model model) {
        model.addAttribute("department", new Department());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/departments");
        return "department_form";
    }

    @PostMapping("/departments")
    public String addDepartment(@Valid Department department, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "add");
            return "department_form";
        }
        department_dao.addDepartment(department);
        return "redirect:/departments";
    }

    @PostMapping("/departments/delete")
    public String deleteDepartment(@RequestParam Map<String, String> body) {
        Long id = Long.parseLong(body.get("id"));
        department_dao.deleteDepartment(id);
        return "redirect:/departments";
    }

    @GetMapping("/departments/{departmentId}/update")
    public String updateDepartmentForm(@PathVariable String departmentId, Model model) {
        model.addAttribute("department", department_dao.getDepartmentById(Long.parseLong(departmentId)));
        model.addAttribute("operation", "update");
        model.addAttribute("action", "/departments/" + departmentId);
        return "department_form";
    }

    @PostMapping("/departments/{departmentId}")
    public String updateDepartment(@Valid Department department,
                                   BindingResult bindingResult,
                                   Model model,
                                   @PathVariable String departmentId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("operation", "update");
            model.addAttribute("action", "/departments/" + departmentId);
            return "department_form";
        }
        department_dao.updateDepartment(Long.parseLong(departmentId), department);
        return "redirect:/departments/{departmentId}";
    }
}
