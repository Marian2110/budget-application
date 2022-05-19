package ro.fasttrackit.budgetapplication.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ro.fasttrackit.budgetapplication.exception.EntityNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(EntityNotFoundException exception) {
        return new ExceptionResponse(exception.getMessage());
    }
//    @ExceptionHandler({Exception.class})
//    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//    public ResponseEntity<ExceptionResponse> handleNotFoundException2(Exception exception) {
//        return ResponseEntity.ok(new ExceptionResponse("exception"));
//    }
}
