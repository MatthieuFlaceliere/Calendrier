package fr.esgi.calendrier.controller.rest;

import fr.esgi.calendrier.business.error.ErrorMessage;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@RestControllerAdvice(annotations = RestController.class)
@Order(1)
public class ErreurRestController {

    Logger logger = LoggerFactory.getLogger(ErreurRestController.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorMessage globalExceptionHandler(Exception ex, WebRequest request) {
        logger.warn(ex.getMessage());

        return new ErrorMessage(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ErrorMessage illegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
        logger.warn(ex.getMessage());

        return new ErrorMessage(
                HttpStatus.BAD_REQUEST.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ErrorMessage entityNotFoundExceptionHandler(EntityNotFoundException ex, WebRequest request) {
        logger.warn(ex.getMessage());

        return new ErrorMessage(
                HttpStatus.NOT_FOUND.value(),
                new Date(),
                ex.getMessage(),
                request.getDescription(false));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorMessage constraintViolationExceptionHandler(ConstraintViolationException ex, WebRequest request) {
        logger.warn(ex.getMessage());

        String message = ex.getConstraintViolations().stream()
                .map(cv -> cv.getPropertyPath() + " " + cv.getMessage())
                .reduce("", (acc, s) -> acc + s + "\n");

        return new ErrorMessage(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                new Date(),
                message,
                request.getDescription(false));
    }
}
