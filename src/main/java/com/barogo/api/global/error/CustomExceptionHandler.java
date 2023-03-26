package com.barogo.api.global.error;

import com.barogo.api.domain.delivery.exception.MaxBeforeDayException;
import com.barogo.api.domain.delivery.exception.NotFoundDeliveryException;
import com.barogo.api.domain.order.exception.NotFoundOrderException;
import com.barogo.api.domain.order.exception.NotUpdateAddressException;
import com.barogo.api.domain.user.exception.AlreadyUserIdException;
import com.barogo.api.domain.user.exception.InvalidPasswordException;
import com.barogo.api.domain.user.exception.NotFoundUserException;
import com.barogo.api.global.common.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        logger.info(ex.getClass().getName());

        final List<String> errors = new ArrayList<>();
        for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getDefaultMessage());
        }

        Response apiError = Response.exceptionResponse()
                .code(Errors.INVALID_FORMAT.getCode())
                .message(errors.toString()).build();

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({MaxBeforeDayException.class})
    public ResponseEntity<Object> handleNotFoundUser(final MaxBeforeDayException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.MAX_BEFORE_DAY.getCode())
                .message(Errors.MAX_BEFORE_DAY.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundUserException.class})
    public ResponseEntity<Object> handleNotFoundUser(final NotFoundUserException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.NOT_FOUND_USER.getCode())
                .message(Errors.NOT_FOUND_USER.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AlreadyUserIdException.class})
    public ResponseEntity<Object> handleAlreadyUserId(final AlreadyUserIdException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.ALREADY_USER.getCode())
                .message(Errors.ALREADY_USER.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidPasswordException.class})
    public ResponseEntity<Object> handleInvalidPassword(final InvalidPasswordException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.INVALID_PASSWORD.getCode())
                .message(Errors.INVALID_PASSWORD.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundOrderException.class})
    public ResponseEntity<Object> handleNotFoundOrder(final NotFoundOrderException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.NOT_FOUND_ORDER.getCode())
                .message(Errors.NOT_FOUND_ORDER.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotUpdateAddressException.class})
    public ResponseEntity<Object> handleNotFoundOrder(final NotUpdateAddressException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.NOT_UPDATE_ADDRESS.getCode())
                .message(Errors.NOT_UPDATE_ADDRESS.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({NotFoundDeliveryException.class})
    public ResponseEntity<Object> handleNotFoundOrder(final NotFoundDeliveryException ex, final HttpServletRequest request) {
        logger.info(ex.getClass().getName());

        Response response = Response.exceptionResponse()
                .code(Errors.NOT_UPDATE_ADDRESS.getCode())
                .message(Errors.NOT_UPDATE_ADDRESS.getDescription()).build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }





}
