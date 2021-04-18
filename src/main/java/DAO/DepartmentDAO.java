package DAO;

import model.Account;
import model.Client;
import model.Department;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface DepartmentDAO {
    void addDepartment(Department department);
    void updateDepartment(Long department_id, Department department);
    Department getDepartmentById(Long department_id);
    ArrayList<Department> getAllDepartments();
    void deleteDepartment(Long department_id);
}
