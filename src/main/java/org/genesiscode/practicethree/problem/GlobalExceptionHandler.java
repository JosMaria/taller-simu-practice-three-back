package org.genesiscode.practicethree.problem;

import org.genesiscode.practicethree.problem.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(ConstraintViolationException exception, HttpServletRequest request) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        HashMap<String, String> paramsErrors = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        constraintViolations.forEach(violation -> paramsErrors.put(
                    getNameParameter(violation.getPropertyPath().toString()),
                    String.format("%s -> %s", violation.getInvalidValue(), violation.getMessage()))
        );

        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .value(httpStatus.value())
                        .name(httpStatus.name())
                        .path(request.getRequestURI())
                        .validations(paramsErrors)
                        .build());
    }

    private String getNameParameter(String value) {
        int positionPoint = value.indexOf('.');
        return value.substring(positionPoint + 1);
    }
}
