package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "client_id_generator")
    @SequenceGenerator(name="client_id_generator", sequenceName = "client_id_seq", allocationSize = 1)
    @Column(name = "client_id")
    private long clientId;

    @NotNull
    @NotBlank
    @Column(name = "client_name", length = 80, nullable = false)
    private String clientName;

    @NotNull
    @NotBlank
    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @NotNull
    @NotBlank
    @Column(name = "email", length = 50)
    private String email;

    @NotNull
    @NotBlank
    @Column(name = "client_address", length = 200)
    private String clientAddress;


    @Column(name = "registration_date", nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    @NotNull
    @NotBlank
    @Column(name = "client_type")
    private String clientType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Client")
    private Set<Account> accounts;

    public Client() {
    }

    public Client(String ClientName, String PhoneNumber, String Email, String ClientAddress,
                  Date RegistrationDate, String ClientType) {
        this.clientName = ClientName;
        this.phoneNumber = PhoneNumber;
        this.email = Email;
        this.clientAddress = ClientAddress;
        this.registrationDate = RegistrationDate;
        this.clientType = ClientType;
    }

    public long getClientId() {
        return clientId;
    }

    public String getClientName() {
        return clientName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getClientAddress() {
        return clientAddress;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public String getFormattedRegistrationDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getRegistrationDate());
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientId(long clientId) {
        this.clientId = clientId;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setClientAddress(String clientAddress) {
        this.clientAddress = clientAddress;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }


    @Override
    public String toString() {
        return "Client{" + "ClientId=" + clientId + ", ClientName=" + clientName + "}";
    }
}