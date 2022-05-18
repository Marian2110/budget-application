package ro.fasttrackit.budgetapplication.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.budgetapplication.entity.Transaction;
import ro.fasttrackit.budgetapplication.exception.EntityNotFoundException;
import ro.fasttrackit.budgetapplication.utils.TransactionType;

import javax.annotation.PostConstruct;
import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @PostConstruct
    private void insert() {
        transactionRepository.save(new Transaction(1L, "apa", TransactionType.BUY, 100.0));
        transactionRepository.save(new Transaction(2L, "paine", TransactionType.SELL, 120.0));
        log.info("inserted");
    }

    private EntityNotFoundException getEntityNotFoundException(Long id, String errorMessage) {
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException(
                id, Transaction.class.getSimpleName());
        log.error(errorMessage, id, entityNotFoundException);
        return entityNotFoundException;
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository
                .findById(id)
                .orElseThrow(() -> getEntityNotFoundException(id, "error in getting transaction {}"));
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(Long id, Transaction transaction) {
        return transactionRepository
                .findById(id)
                .map(existingTransaction -> {
                    existingTransaction.setProduct(transaction.getProduct());
                    existingTransaction.setType(transaction.getType());
                    existingTransaction.setAmount(transaction.getAmount());
                    return transactionRepository.save(existingTransaction);
                }).orElseThrow(() -> getEntityNotFoundException(id, "error in updating transaction {}"));
    }

    public Transaction delete(Long id) {
        return transactionRepository
                .findById(id)
                .map(existingTransaction -> {
                    transactionRepository.delete(existingTransaction);
                    return existingTransaction;
                }).orElseThrow(() -> getEntityNotFoundException(id, "error in deleting transaction {}"));
    }
}
