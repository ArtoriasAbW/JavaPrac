package DAO;

import model.Department;

import java.util.ArrayList;


public interface DepartmentDAO {
    void addDepartment(Department department);
    void updateDepartment(Long department_id, Department department);
    Department getDepartmentById(Long department_id);
    ArrayList<Department> getAllDepartments();
    void deleteDepartment(Long department_id);
}
