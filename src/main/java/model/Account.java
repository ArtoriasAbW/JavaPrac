package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number", length = 12)
    private String accountNumber;

    @Column(name = "account_status")
    private String accountStatus;

    @ManyToOne
    @JoinColumn(name = "client", referencedColumnName = "client_id")
    private Client Client;

    @Column(name = "account_balance")
    private double accountBalance;

    @ManyToOne
    @JoinColumn(name = "department", referencedColumnName = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "account_type", referencedColumnName = "type_id")
    private AccountType type;

    @Column(name = "opening_date")
    private Date openingDate;

    public Account() {

    }

    public Account(String AccountNumber, String AccountStatus, Client Client,
                   double AccountBalance, Department Department, AccountType Type, Date OpeningDate) {
        this.accountNumber = AccountNumber;
        this.accountStatus = AccountStatus;
        this.Client = Client;
        this.accountBalance = AccountBalance;
        this.department = Department;
        this.type = Type;
        this.openingDate = OpeningDate;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public Client getClient() {
        return Client;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public Department getDepartment() {
        return department;
    }

    public AccountType getType() {
        return type;
    }

    public Date getOpeningDate() {
        return openingDate;
    }

    public String getFormattedOpeningDate() {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getOpeningDate());
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public void setClient(Client client) {
        this.Client = client;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public void setOpeningDate(Date openingDate) {
        this.openingDate = openingDate;
    }

    @Override
    public String toString() {
        return "Account{" + "AccountNumber=" + accountNumber + ", AccountStatus=" + accountStatus + "}";
    }
}
