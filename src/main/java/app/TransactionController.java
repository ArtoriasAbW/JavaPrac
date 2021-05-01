package app;

import DAO.impl.TransactionDAOimpl;
import model.Transaction;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@Controller
public class TransactionController {
    @GetMapping("/transactions")
    @ResponseBody
    public ArrayList<Transaction> transactions() {
        TransactionDAOimpl transaction_dao = new TransactionDAOimpl();
        return transaction_dao.getAllTransactions();
    }

}
