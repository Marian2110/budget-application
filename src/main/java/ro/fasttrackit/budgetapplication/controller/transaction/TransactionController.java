package ro.fasttrackit.budgetapplication.controller.transaction;


import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ro.fasttrackit.budgetapplication.model.dto.TransactionDTO;
import ro.fasttrackit.budgetapplication.model.entity.Transaction;
import ro.fasttrackit.budgetapplication.service.transport.TransactionService;
import ro.fasttrackit.budgetapplication.utils.TransactionInfo;
import ro.fasttrackit.budgetapplication.model.mapper.TransactionMapper;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/transactions")
@AllArgsConstructor
@Validated
public class TransactionController {
    private final TransactionService transactionService;
    private final TransactionMapper transactionMapper;

    @GetMapping(path = "{id}")
    public Transaction getTransaction(@PathVariable Long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    public Transaction addTransaction(@Valid @RequestBody TransactionDTO transactionDTO) {
        Transaction transaction = transactionMapper.mapToEntity(transactionDTO);
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

    @GetMapping("/reports/type")
    public Map<String, List<TransactionInfo>> getTransactionsGroupedByType() {
        return transactionService.getTransactionsGroupedByType();
    }

    @GetMapping("/reports/product")
    public Map<String, List<TransactionInfo>> getTransactionsGroupedByProduct() {
        return transactionService.getTransactionsGroupedByProduct();
    }

    @GetMapping()
    public List<Transaction> findAllSortedAndPaginated(@RequestParam(required = false) String sortBy,
                                                       @RequestParam(required = false) String order,
                                                       @RequestParam(required = false) Integer page,
                                                       @RequestParam(required = false) Integer size) {

        return transactionService.findAllSortedAndPaginated(sortBy, order, page, size);
    }

}

