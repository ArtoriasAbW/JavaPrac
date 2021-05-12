package app;

import DAO.impl.TransactionDAOimpl;
import model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class TransactionController {

    @GetMapping("/transactions")
    public String transactions(Model model) {
        TransactionDAOimpl transaction_dao = new TransactionDAOimpl();
        model.addAttribute("transactions", transaction_dao.getAllTransactions());
        return "transaction_list";
    }

    @GetMapping("/transactions/{transactionId}")
    public String transaction(@PathVariable String transactionId, Model model) {
        Transaction transaction = new TransactionDAOimpl().getTransactionById(transactionId);
        model.addAttribute("transactionId", transaction.getTransactionId());
        model.addAttribute("sender", transaction.getSender());
        model.addAttribute("receiver", transaction.getReceiver());
        model.addAttribute("amount", transaction.getAmount());
        model.addAttribute("time", transaction.getFormattedTransactionDate());
        return "transaction";
    }

}
