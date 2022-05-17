package ro.fasttrackit.budgetapplication.service;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.fasttrackit.budgetapplication.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
