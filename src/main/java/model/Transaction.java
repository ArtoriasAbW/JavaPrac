package model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "account_history")
public class Transaction {

    @Id
    @Column(name = "transaction_id", length = 15)
    private String transactionId;

    @Column(name = "transaction_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date transactionDate;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "account_number")
    private Account sender;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "account_number")
    private Account receiver;

    @Column(name = "amount")
    private float amount;

    public Transaction() {

    }

    public Transaction(String transactionId, Date transactionDate, Account sender, Account receiver, float amount) {
        this.transactionId = transactionId;
        this.transactionDate = transactionDate;
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public String getFormattedTransactionDate() {
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getTransactionDate());
    }

    public Account getSender() {
        return sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public float getAmount() {
        return amount;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" + "TransactionDate=" + transactionDate + ", TransactionDate=" + transactionDate + "}";
    }
}
