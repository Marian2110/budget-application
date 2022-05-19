package ro.fasttrackit.budgetapplication.service;

import org.springframework.data.repository.CrudRepository;
import ro.fasttrackit.budgetapplication.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
}
