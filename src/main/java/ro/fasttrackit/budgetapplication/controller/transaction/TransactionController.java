package ro.fasttrackit.budgetapplication.controller.transaction;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.budgetapplication.entity.Transaction;
import ro.fasttrackit.budgetapplication.service.TransactionService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Validated
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
    public Transaction addTransaction( @Valid @RequestBody Transaction transaction) {
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

