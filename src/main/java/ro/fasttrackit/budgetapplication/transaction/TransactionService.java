package ro.fasttrackit.budgetapplication.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    private EntityNotFoundException getEntityNotFoundException(Long id, String errorMessage) {
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException(
                "Transaction with id " + id + " not found");

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
