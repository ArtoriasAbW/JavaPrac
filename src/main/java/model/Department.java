package model;

import javax.persistence.*;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @SequenceGenerator(name="department_id_generator", sequenceName="department_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="department_id_generator")
    @Column(name="department_id")
    private long DepartmentId;

    @Column(name="department_name", length = 80)
    private String DepartmentName;

    @Column(name="department_address", length = 200)
    private String DepartmentAddress;

    @Column(name="department_phone_number", length = 20)
    private String DepartmentsPhoneNumber;

    public Department() {

    }

    public Department(String DepartmentName,
                      String DepartmentAddress, String DepartmentPhoneNumber) {
        this.DepartmentName = DepartmentName;
        this.DepartmentAddress = DepartmentAddress;
        this.DepartmentsPhoneNumber = DepartmentPhoneNumber;
    }

    public long getDepartmentId() {
        return DepartmentId;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public String getDepartmentAddress() {
        return DepartmentAddress;
    }

    public String getDepartmentsPhoneNumber() {
        return DepartmentsPhoneNumber;
    }

    public void setDepartmentId(long departmentId) {
        this.DepartmentId = departmentId;
    }

    public void setDepartmentName(String departmentName) {
        this.DepartmentName = departmentName;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.DepartmentAddress = departmentAddress;
    }

    public void setDepartmentsPhoneNumber(String departmentsPhoneNumber) {
        this.DepartmentsPhoneNumber = departmentsPhoneNumber;
    }

    @Override
    public String toString() {
        return "Department{" + "DepartmentId=" + DepartmentId + ", DepartmentName=" + DepartmentName + "}";
    }
}
