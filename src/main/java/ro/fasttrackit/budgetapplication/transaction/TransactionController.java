package ro.fasttrackit.budgetapplication.transaction;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/vi/transactions")
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping()
    public List<Transaction> getAllTransactions() {
        return transactionService.getTransactions();
    }

    @GetMapping(path = "{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction addTransaction(@RequestBody Transaction transaction) {
        return transactionService.save(transaction);
    }

    @PutMapping(path = "{id}")
    public Transaction updateTransaction(@PathVariable Long id, @RequestBody Transaction transaction) {
        return transactionService.update(id, transaction);
    }

    @DeleteMapping(path = "{id}")
    public Transaction deleteTransaction(@PathVariable Long id) {
        return transactionService.delete(id);
    }

}

