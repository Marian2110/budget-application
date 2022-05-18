package ro.fasttrackit.budgetapplication;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.fasttrackit.budgetapplication.exception.EntityNotFoundException;
import ro.fasttrackit.budgetapplication.exception.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(EntityNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
}
