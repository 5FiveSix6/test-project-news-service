package yuriy.spring.news_service.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import yuriy.spring.news_service.exception.InvalidModeratorIdException;
import yuriy.spring.news_service.exception.InvalidUserRoleException;
import yuriy.spring.news_service.exception.NewsAlreadyPublishedException;
import yuriy.spring.news_service.exception.NewsNotFoundException;
import yuriy.spring.news_service.exception.UserAlreadyExistException;
import yuriy.spring.news_service.exception.UserNotFoundException;

import java.util.Locale;

@ControllerAdvice
@RequiredArgsConstructor
public class NewsServiceControllerAdvice {

    private final MessageSource messageSource;

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ProblemDetail> handleBindException(BindException e, Locale locale) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                messageSource.getMessage("errors.400.bad_request", new Object[0], locale));
        problemDetails.setProperty("errors",
                e.getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }

    @ExceptionHandler({
            NewsNotFoundException.class,
            UserNotFoundException.class})
    public ResponseEntity<ProblemDetail> handleNotFoundException(UserNotFoundException e,
                                                                 Locale locale) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND,
                messageSource.getMessage(e.getMessage(), new Object[0], locale));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetails);
    }

    @ExceptionHandler({
            InvalidModeratorIdException.class,
            NewsAlreadyPublishedException.class,
            InvalidUserRoleException.class,
            UserAlreadyExistException.class})
    public ResponseEntity<ProblemDetail> handleBadRequestException(Exception e,
                                                                   Locale locale) {
        var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST,
                messageSource.getMessage(e.getMessage(), new Object[0], locale));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetails);
    }
}
