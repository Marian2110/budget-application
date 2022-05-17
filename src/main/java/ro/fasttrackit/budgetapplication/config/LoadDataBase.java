package ro.fasttrackit.budgetapplication.transaction.config;

import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ro.fasttrackit.budgetapplication.transaction.Transaction;
import ro.fasttrackit.budgetapplication.transaction.TransactionRepository;

@Configuration
public class LoadDataBase {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoadDataBase.class);

    @Bean
    CommandLineRunner initDataBase(TransactionRepository transactionRepository) {
        return args -> {
            log.info("Preloading " + transactionRepository.save(new Transaction("apple", "SELL", 100.0)));
            log.info("Preloading " + transactionRepository.save(new Transaction("phone", "BUY", 120.0)));
        };
    }
}
