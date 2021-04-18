package model;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "account_history")
public class Transaction {

    @Id
    @Column(name = "transaction_id", length = 15)
    private String TransactionId;

    @Column(name = "transaction_date")
    private Date TransactionDate;

    @ManyToOne
    @JoinColumn(name = "sender", referencedColumnName = "account_number")
    private Account Sender;

    @ManyToOne
    @JoinColumn(name = "receiver", referencedColumnName = "account_number")
    private Account Receiver;

    @Column(name = "amount")
    private float Amount;

    public Transaction() {

    }

    public Transaction(String transactionId, Date transactionDate, Account sender, Account receiver, float amount) {
        this.TransactionId = transactionId;
        this.TransactionDate = transactionDate;
        this.Sender = sender;
        this.Receiver = receiver;
        this.Amount = amount;
    }

    public String getTransactionId() {
        return TransactionId;
    }

    public Date getTransactionDate() {
        return TransactionDate;
    }

    public String getFormattedTransactionDate() {
        String pattern = "yyyy-MM-dd hh:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(getTransactionDate());
    }

    public Account getSender() {
        return Sender;
    }

    public Account getReceiver() {
        return Receiver;
    }

    public float getAmount() {
        return Amount;
    }

    public void setTransactionId(String transactionId) {
        TransactionId = transactionId;
    }

    public void setTransactionDate(Date transactionDate) {
        TransactionDate = transactionDate;
    }

    public void setSender(Account sender) {
        Sender = sender;
    }

    public void setReceiver(Account receiver) {
        Receiver = receiver;
    }

    public void setAmount(float amount) {
        Amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" + "TransactionDate=" + TransactionDate + ", TransactionDate=" + TransactionDate + "}";
    }
}
