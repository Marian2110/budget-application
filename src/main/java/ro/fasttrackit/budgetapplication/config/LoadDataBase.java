package ro.fasttrackit.budgetapplication.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.fasttrackit.budgetapplication.transaction.Transaction;
import ro.fasttrackit.budgetapplication.transaction.TransactionRepository;

@Slf4j
@Configuration
public class LoadDataBase {
    @Bean
    CommandLineRunner initDataBase(TransactionRepository transactionRepository) {
        return args -> {
            log.info("Preloading " + transactionRepository.save(new Transaction("apple", "SELL", 100.0)));
            log.info("Preloading " + transactionRepository.save(new Transaction("phone", "BUY", 120.0)));
        };
    }
}
