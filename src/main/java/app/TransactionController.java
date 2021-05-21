package app;

import DAO.impl.*;
import model.Account;
import model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;


@Controller
public class TransactionController {
    static TransactionDAOimpl transaction_dao = new TransactionDAOimpl();
    @GetMapping("/transactions")
    public String transactions(Model model) {
        model.addAttribute("transactions", transaction_dao.getAllTransactions());
        return "transaction_list";
    }

    @GetMapping("/transactions/{transactionId}")
    public String transaction(@PathVariable String transactionId, Model model) {
        Transaction transaction = transaction_dao.getTransactionById(transactionId);
        model.addAttribute("transactionId", transaction.getTransactionId());
        model.addAttribute("sender", transaction.getSender());
        model.addAttribute("receiver", transaction.getReceiver());
        model.addAttribute("amount", transaction.getAmount());
        model.addAttribute("time", transaction.getFormattedTransactionDate());
        return "transaction";
    }

    @GetMapping("/transactions/form")
    public String transactionForm(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("allaccounts", new AccountDAOimpl().getAllAccounts());
        model.addAttribute("action", "/transactions");
        model.addAttribute("operation", "add");
        return "transaction_form";
    }

    @PostMapping("/transactions")
    public String addTransaction(@Valid Transaction transaction, BindingResult bindingResult, Model model) {
        String sender_number = transaction.getSender().getAccountNumber();
        String receiver_number = transaction.getReceiver().getAccountNumber();
        if (bindingResult.hasErrors()) {
            model.addAttribute("allaccounts", new AccountDAOimpl().getAllAccounts());
            model.addAttribute("action", "/transactions");
            model.addAttribute("operation", "add");
            return "transaction_form";
        }
        transaction.setSender(new AccountDAOimpl().getAccountByNumber(sender_number));
        transaction.setReceiver(new AccountDAOimpl().getAccountByNumber(receiver_number));
        transaction_dao.addTransaction(transaction);
        return "redirect:/transactions";
    }

    @PostMapping("/transactions/delete")
    public String deleteTransaction(@RequestParam Map<String, String> body) {
        transaction_dao.deleteTransaction(body.get("id"));
        return "redirect:/transactions";
    }

    @GetMapping("/transactions/{transactionId}/update")
    public String updateAccountForm(@PathVariable String transactionId, Model model) {
        model.addAttribute("transaction", transaction_dao.getTransactionById(transactionId));
        model.addAttribute("allaccounts", new AccountDAOimpl().getAllAccounts());
        model.addAttribute("operation", "add");
        model.addAttribute("action", "/transactions/" + transactionId);
        return "transaction_form";
    }

    @PostMapping("transactions/{transactionId}")
    public String updateAccount(@Valid Transaction transaction,
                                BindingResult bindingResult,
                                Model model,
                                @PathVariable String transactionId) {
        String sender_number = transaction.getSender().getAccountNumber();
        String receiver_number = transaction.getReceiver().getAccountNumber();
        if (bindingResult.hasErrors()) {
            model.addAttribute("allaccounts", new AccountDAOimpl().getAllAccounts());
            model.addAttribute("action", "/transactions/" + transactionId);
            model.addAttribute("operation", "update");
            return "transaction_form";
        }
        transaction.setSender(new AccountDAOimpl().getAccountByNumber(sender_number));
        transaction.setReceiver(new AccountDAOimpl().getAccountByNumber(receiver_number));
        transaction_dao.updateTransaction(transactionId, transaction);
        return "redirect:/transactions/{transactionId}";
    }

}
