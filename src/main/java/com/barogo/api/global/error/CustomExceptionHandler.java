package com.barogo.api.global.error;

import com.barogo.api.domain.user.exception.AlreadyUserIdException;
import com.barogo.api.domain.user.exception.NotFoundUserException;
import com.barogo.api.global.common.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NotFoundUserException.class})
    public ResponseEntity<Object> handleAlreadyCaptured(final NotFoundUserException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.NOT_FOUND_USER.getCode())
                .message(Errors.NOT_FOUND_USER.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AlreadyUserIdException.class})
    public ResponseEntity<Object> handleAlreadyCaptured(final AlreadyUserIdException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.NOT_FOUND_USER.getCode())
                .message(Errors.NOT_FOUND_USER.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
