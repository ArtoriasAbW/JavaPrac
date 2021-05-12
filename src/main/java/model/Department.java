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
    private long DepartmentId;

    @Column(name="department_name", length = 80)
    @NotNull
    @NotBlank
    @Size(min=5, max=40)
    private String DepartmentName;

    @Column(name="department_address", length = 200)
    @NotNull
    @NotBlank
    @Size(min=5, max=50)
    private String DepartmentAddress;

    @Column(name="department_phone_number", length = 20)
    @NotNull
    @NotBlank
    @Size(min=5, max=20)
    private String DepartmentPhoneNumber;

    public Department() {

    }

    public Department(String DepartmentName,
                      String DepartmentAddress, String DepartmentPhoneNumber) {
        this.DepartmentName = DepartmentName;
        this.DepartmentAddress = DepartmentAddress;
        this.DepartmentPhoneNumber = DepartmentPhoneNumber;
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

    public String getDepartmentPhoneNumber() {
        return DepartmentPhoneNumber;
    }

    public void setDepartmentName(String departmentName) {
        this.DepartmentName = departmentName;
    }

    public void setDepartmentAddress(String departmentAddress) {
        this.DepartmentAddress = departmentAddress;
    }

    public void setDepartmentPhoneNumber(String departmentPhoneNumber) {
        this.DepartmentPhoneNumber = departmentPhoneNumber;
    }

    @Override
    public String toString() {
        return "Department{" + "DepartmentId=" + DepartmentId + ", DepartmentName=" + DepartmentName + "}";
    }
}
