package DAO.impl;

import DAO.DepartmentDAO;
import factory.MyFactory;
import model.Account;
import model.Client;
import model.Department;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;


public class DepartmentDAOimpl implements DepartmentDAO {

    public DepartmentDAOimpl() {

    }

    @Override
    public void addDepartment(Department department) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        session.save(department);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public void updateDepartment(Long department_id, Department department) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Department dep = getDepartmentById(department_id);
        session.evict(dep);
        dep.setDepartmentAddress(department.getDepartmentAddress());
        dep.setDepartmentName(department.getDepartmentName());
        dep.setDepartmentPhoneNumber(department.getDepartmentPhoneNumber());
        session.update(dep);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public Department getDepartmentById(Long department_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Department department = session.get(Department.class, department_id);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return department;
    }

    @Override
    public ArrayList<Department> getAllDepartments() {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Query query = session.createQuery("select d from Department d");
        ArrayList<Department> departments = (ArrayList<Department>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return departments;
    }

    @Override
    public void deleteDepartment(Long department_id) {

        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Department department = getDepartmentById(department_id);
        session.delete(department);
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
    }

    @Override
    public ArrayList<Account> getAllDepartmentAccounts(Long department_id) {
        Session session = MyFactory.getFactory().openSession();
        session.beginTransaction();
        Department department = getDepartmentById(department_id);
        Query query = session.createQuery("FROM Account A WHERE A.Department = :department");
        query.setParameter("department", department);
        ArrayList<Account> accounts = (ArrayList<Account>) query.list();
        session.getTransaction().commit();
        if (session.isOpen()) {
            session.close();
        }
        return accounts;
    }
}
