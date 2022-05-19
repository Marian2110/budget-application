package ro.fasttrackit.budgetapplication.exception;

import lombok.extern.slf4j.Slf4j;
import ro.fasttrackit.budgetapplication.entity.Transaction;

@Slf4j
public class EntityNotFoundException extends RuntimeException {
    private final Long id;
    private final String entityName;
    public EntityNotFoundException(Long id, String entityName) {
        super(entityName + " with id " + id + " not found");
        this.entityName = entityName;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getEntityName() {
        return entityName;
    }

    public static EntityNotFoundException createException(Long id, String errorMessage, String entityName) {
        EntityNotFoundException entityNotFoundException = new EntityNotFoundException(
                id, entityName);
        log.error(errorMessage, id, entityNotFoundException);
        return entityNotFoundException;
    }
}
