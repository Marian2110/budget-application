package ro.fasttrackit.budgetapplication.transaction;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TransactionService {

    private final TransactionRepository transactionRepository;

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public Transaction findById(Long id) {
        return transactionRepository
                .findById(id)
                .orElseThrow(
                        () -> {
                            EntityNotFoundException entityNotFoundException = new EntityNotFoundException(
                                    "Transaction with id " + id + " not found");
                            log.error("error in getting transaction {}", id, entityNotFoundException);
                            return entityNotFoundException;
                        }
                );
    }

    public Transaction save(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Transaction update(Long id, Transaction transaction) {
        return transactionRepository.findById(id).map(
                existingTransaction -> {
                    existingTransaction.setProduct(transaction.getProduct());
                    existingTransaction.setType(transaction.getType());
                    existingTransaction.setAmount(transaction.getAmount());
                    return transactionRepository.save(existingTransaction);
                }).orElseThrow(
                () -> {
                    EntityNotFoundException entityNotFoundException = new EntityNotFoundException(
                            "Transaction with id " + id + " not found");
                    log.error("error in updating transaction {}", id, entityNotFoundException);
                    return entityNotFoundException;
                }
        );
    }

    public Transaction delete(Long id) {
        return transactionRepository.findById(id).map(
                existingTransaction -> {
                    transactionRepository.delete(existingTransaction);
                    return existingTransaction;
                }).orElseThrow(
                () -> {
                    EntityNotFoundException entityNotFoundException = new EntityNotFoundException(
                            "Transaction with id " + id + " not found");
                    log.error("error in deleting transaction {}", id, entityNotFoundException);
                    return entityNotFoundException;
                }
        );
    }
}
