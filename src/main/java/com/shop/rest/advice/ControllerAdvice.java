package com.shop.rest.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.shop.execption.ApiError;
import com.shop.execption.EntityNotFoundException;

/**
 *
 * @author Arpan Khandelwal
 *
 */
@RestControllerAdvice
public class ControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseBody
    public ApiError handlePackageNotFoundException(final EntityNotFoundException entityNotFoundException) {
        return new ApiError(entityNotFoundException.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ApiError handleIllegalArgumentException(final IllegalArgumentException illegalArgumentException) {
        return new ApiError(illegalArgumentException.getMessage());
    }

    // TODO Need to handle specific exceptions
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public void handleIllegalArgumentException(final Exception exception) {
    }
}
