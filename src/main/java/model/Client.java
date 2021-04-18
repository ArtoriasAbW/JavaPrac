package model;

import javax.persistence.*;
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
    private long ClientId;

    @Column(name = "client_name", length = 80, nullable = false)
    private String ClientName;

    @Column(name = "phone_number", length = 20)
    private String PhoneNumber;

    @Column(name = "email", length = 50)
    private String Email;

    @Column(name = "client_address", length = 200)
    private String ClientAddress;

    @Column(name = "registration_date", nullable = false)
    private Date RegistrationDate;

    @Column(name = "client_type")
    private String ClientType;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Client")
    private Set<Account> accounts;

    public Client() {
    }

    public Client(String ClientName, String PhoneNumber, String Email, String ClientAddress,
                  Date RegistrationDate, String ClientType) {
        this.ClientName = ClientName;
        this.PhoneNumber = PhoneNumber;
        this.Email = Email;
        this.ClientAddress = ClientAddress;
        this.RegistrationDate = RegistrationDate;
        this.ClientType = ClientType;
    }

    public long getClientId() {
        return ClientId;
    }

    public String getClientName() {
        return ClientName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public String getEmail() {
        return Email;
    }

    public String getClientAddress() {
        return ClientAddress;
    }

    public Date getRegistrationDate() {
        return RegistrationDate;
    }

    public String getFormattedRegistrationDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getRegistrationDate());
    }

    public String getClientType() {
        return ClientType;
    }

    public void setClientId(long clientId) {
        this.ClientId = clientId;
    }

    public void setClientName(String clientName) {
        this.ClientName = clientName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.PhoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.Email = email;
    }

    public void setClientAddress(String clientAddress) {
        this.ClientAddress = clientAddress;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.RegistrationDate = registrationDate;
    }

    public void setClientType(String clientType) {
        this.ClientType = clientType;
    }


    @Override
    public String toString() {
        return "Client{" + "ClientId=" + ClientId + ", ClientName=" + ClientName + "}";
    }
}