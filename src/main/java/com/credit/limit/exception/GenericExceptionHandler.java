package com.credit.limit.exception;

import com.credit.limit.pojos.request.response.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> handleGenericException(Exception exception, WebRequest request){
        return new ResponseEntity<>(new BaseResponse<>(exception.getMessage(),"failed"), HttpStatus.BAD_REQUEST);
    }
}
