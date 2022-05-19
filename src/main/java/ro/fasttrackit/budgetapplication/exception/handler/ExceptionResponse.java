package ro.fasttrackit.budgetapplication.exception.handler;

public class ExceptionResponse {
    private final String message;

    /**
     * id
     * fields lista de fieldError clasa field, eroare , suggestion
     * entity name
     *
     */

    public ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
