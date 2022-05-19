package ro.fasttrackit.budgetapplication.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.fasttrackit.budgetapplication.entity.Transaction;
import ro.fasttrackit.budgetapplication.exception.EntityNotFoundException;

import java.util.List;

@AllArgsConstructor
@Slf4j
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private String entityName = Transaction.class.getSimpleName();

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository
                .findById(id)
                .orElseThrow(() -> EntityNotFoundException
                        .createException(id, "error in getting transaction {}", entityName));
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
                }).orElseThrow(() -> EntityNotFoundException
                        .createException(id, "error in updating transaction {}", entityName));
    }

    public Transaction delete(Long id) {
        return transactionRepository
                .findById(id)
                .map(existingTransaction -> {
                    transactionRepository.delete(existingTransaction);
                    return existingTransaction;
                }).orElseThrow(() -> EntityNotFoundException
                        .createException(id, "error in deleting transaction {}", entityName));
    }
}
