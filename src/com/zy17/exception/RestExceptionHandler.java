package com.zy17.exception;

import com.zy17.controller.BlobController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.xml.bind.ValidationException;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA. User: Administrator Date: 13-6-19 Time: 下午3:32 To
 * change this template use File | Settings | File Templates.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
    private static final Logger log = Logger.getLogger(RestExceptionHandler.class.getName());

    @ExceptionHandler(value = {IllegalArgumentException.class, NumberFormatException.class, AuthenticationException.class, Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest request) {
        log.severe(ex.getMessage());
        HttpHeaders headers = new HttpHeaders();
          if (ex instanceof IllegalArgumentException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleException((IllegalArgumentException) ex, headers,
                    status, request);
        } else if (ex instanceof ValidationException) {
            HttpStatus status = HttpStatus.BAD_REQUEST;
            return handleException((NumberFormatException) ex, headers, status,
                    request);
        } else if (ex instanceof AuthenticationException) {
            HttpStatus status = HttpStatus.NETWORK_AUTHENTICATION_REQUIRED;
            return handleException((AuthenticationException) ex, headers,
                    status, request);
        } else if (ex instanceof Exception) {
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleException(ex, headers,
                    status, request);
        } else {
            logger.warn("Unknown exception type: " + ex.getClass().getName());
            HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
            return handleExceptionInternal(ex, null, headers, status, request);
        }
    }

    protected ResponseEntity<Object> handleException(Exception ex,
                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {
        return handleExceptionInternal(ex, ex, headers, status,
                request);
    }

    public RestExceptionHandler() {
        super();
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return null;
    }
}
