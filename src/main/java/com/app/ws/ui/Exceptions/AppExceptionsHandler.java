package com.app.ws.ui.Exceptions;


import com.app.ws.ui.model.response.ErrorMessage;
import com.app.ws.ui.model.response.ErrorMessages;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class AppExceptionsHandler {


    @ExceptionHandler(value = {UserServiceExceptions.class})
    public ResponseEntity<Object> handleUserServiceException(UserServiceExceptions ex, WebRequest wb){

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<Object>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }



    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleOtherExceptions(UserServiceExceptions ex, WebRequest wb){

        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<Object>(errorMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
