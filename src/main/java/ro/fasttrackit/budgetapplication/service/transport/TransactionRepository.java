package ro.fasttrackit.budgetapplication.service.transport;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ro.fasttrackit.budgetapplication.model.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByConfirmedTrueAndCreatedAtBetween(LocalDateTime startDate, LocalDateTime endDate);

    @Modifying
    @Transactional
    @Query("UPDATE Transaction t SET t.confirmed = true WHERE t.confirmed = false")
    void confirmAll();

}
