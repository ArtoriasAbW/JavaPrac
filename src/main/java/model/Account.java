package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number", length = 12)
    private String AccountNumber;

    @Column(name = "account_status")
    private String AccountStatus;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "client_id")
    private Client Client;

    @Column(name = "account_balance")
    private double AccountBalance;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "department_id")
    private Department Department;

    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "type_id")
    private AccountType Type;

    @Column(name = "opening_date")
    private Date OpeningDate;

    public Account() {

    }

    public Account(String AccountNumber, String AccountStatus, Client Client,
                   double AccountBalance, Department Department, AccountType Type, Date OpeningDate) {
        this.AccountNumber = AccountNumber;
        this.AccountStatus = AccountStatus;
        this.Client = Client;
        this.AccountBalance = AccountBalance;
        this.Department = Department;
        this.Type = Type;
        this.OpeningDate = OpeningDate;
    }

    public String getAccountNumber() {
        return AccountNumber;
    }

    public String getAccountStatus() {
        return AccountStatus;
    }

    public Client getClient() {
        return Client;
    }

    public double getAccountBalance() {
        return AccountBalance;
    }

    public Department getDepartment() {
        return Department;
    }

    public AccountType getType() {
        return Type;
    }

    public Date getOpeningDate() {
        return OpeningDate;
    }

    public String getFormattedOpeningDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getOpeningDate());
    }

    public void setAccountNumber(String accountNumber) {
        AccountNumber = accountNumber;
    }

    public void setAccountStatus(String accountStatus) {
        AccountStatus = accountStatus;
    }

    public void setClient(Client client) {
        Client = client;
    }

    public void setAccountBalance(double accountBalance) {
        AccountBalance = accountBalance;
    }

    public void setDepartment(Department department) {
        Department = department;
    }

    public void setType(AccountType type) {
        Type = type;
    }

    public void setOpeningDate(Date openingDate) {
        OpeningDate = openingDate;
    }

    @Override
    public String toString() {
        return "Account{" + "AccountNumber=" + AccountNumber + ", AccountStatus=" + AccountStatus + "}";
    }
}
