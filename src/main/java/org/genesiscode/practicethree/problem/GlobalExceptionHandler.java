package org.genesiscode.practicethree.problem;

import org.genesiscode.practicethree.problem.exceptions.RelativePrimeException;
import org.genesiscode.practicethree.problem.response.RequestErrorResponse;
import org.genesiscode.practicethree.problem.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Set;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.PRECONDITION_FAILED;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> handleConstraintViolationException(ConstraintViolationException exception, HttpServletRequest request) {
        HashMap<String, String> paramsErrors = new HashMap<>();
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        constraintViolations.forEach(violation -> paramsErrors.put(
                    getNameParameter(violation.getPropertyPath().toString()),
                    String.format("%s -> %s", violation.getInvalidValue(), violation.getMessage()))
        );

        return ResponseEntity.badRequest()
                .body(ValidationErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(BAD_REQUEST.value())
                        .error(BAD_REQUEST.name())
                        .path(request.getRequestURI())
                        .validations(paramsErrors)
                        .build());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<RequestErrorResponse> handleMissingServletRequestParameter(MissingServletRequestParameterException exception, HttpServletRequest request) {
        return ResponseEntity.badRequest()
                .body(RequestErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(BAD_REQUEST.value())
                        .error(BAD_REQUEST.name())
                        .path(request.getRequestURI())
                        .message(exception.getMessage())
                        .build());
    }

    private String getNameParameter(String value) {
        int positionPoint = value.indexOf('.');
        return value.substring(positionPoint + 1);
    }

    @ExceptionHandler(RelativePrimeException.class)
    public ResponseEntity<RequestErrorResponse> handleRelativePrime(RelativePrimeException exception, HttpServletRequest request) {
        HttpStatus httpCode = PRECONDITION_FAILED;
        return ResponseEntity
                .status(httpCode)
                .body(RequestErrorResponse.builder()
                        .timestamp(LocalDateTime.now())
                        .status(httpCode.value())
                        .error(httpCode.name())
                        .path(request.getRequestURI())
                        .message(exception.getMessage())
                        .build());
    }
}
