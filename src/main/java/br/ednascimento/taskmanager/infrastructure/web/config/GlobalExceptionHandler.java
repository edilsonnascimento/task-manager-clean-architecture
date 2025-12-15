package br.ednascimento.taskmanager.infrastructure.web.config;

import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTaskException.class)
    public ProblemDetail handleNotFoundException(InvalidTaskException ex) {
        return ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                ex.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {

        var problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.CONFLICT,
                "Some fields are missing or invalid."
        );
        problem.setTitle("Validation Error");
        problem.setProperty("errorCode", "VALIDATION_ERROR");
        var fieldErrors = new HashMap<>();
        for (FieldError fieldError : ex.getBindingResult().getFieldErrors())
            fieldErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        problem.setProperty("fields", fieldErrors);
        return problem;
    }
}
