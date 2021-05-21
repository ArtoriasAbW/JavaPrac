package model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @SequenceGenerator(name="department_id_generator", sequenceName="department_id_seq", allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="department_id_generator")
    @Column(name="department_id")
    private long departmentId;

    @Column(name="department_name", length = 80)
    @NotBlank
    @Size(min=5, max=40)
    private String departmentName;

    @Column(name="department_address", length = 200)
    @NotBlank
    @Size(min=5, max=50)
    private String departmentAddress;

    @Column(name="department_phone_number", length = 20)
    @NotBlank
    @Size(min=5, max=20)
    private String departmentPhoneNumber;

    public Department() {

    }

    public Department(String DepartmentName,
                      String DepartmentAddress, String DepartmentPhoneNumber) {
        this.departmentName = DepartmentName;
        this.departmentAddress = DepartmentAddress;
        this.departmentPhoneNumber = DepartmentPhoneNumber;
    }


    public long getDepartmentId() {
        return departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public String getDepartmentAddress() {
        return departmentAddress;
    }

    public String getDepartmentPhoneNumber() {
        return departmentPhoneNumber;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.departmentAddress = departmentAddress;
    }

    public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
        this.departmentPhoneNumber = departmentPhoneNumber;
    }

    @Override
    public String toString() {
        return "Department{" + "DepartmentId=" + departmentId + ", DepartmentName=" + departmentName + "}";
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }
}
