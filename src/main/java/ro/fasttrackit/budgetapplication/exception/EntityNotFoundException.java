package ro.fasttrackit.budgetapplication.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Long id, String entityName) {
        super(entityName + " with id " + id + " not found");
    }
}
