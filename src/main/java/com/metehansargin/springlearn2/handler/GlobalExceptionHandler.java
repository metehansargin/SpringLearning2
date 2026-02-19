package com.metehansargin.springlearn2.handler;

import com.metehansargin.springlearn2.exception.BaseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {BaseException.class})
    public ResponseEntity<ApiError> handleBaseException(BaseException baseException, WebRequest webRequest){

        return ResponseEntity.badRequest().body(createApiError(baseException.getMessage(),webRequest));

    }

    public <E> ApiError<E> createApiError(E message,WebRequest webRequest){

        ApiError<E> apiError=new ApiError<>();
        apiError.setStatus(HttpStatus.BAD_REQUEST.value());

        Exception<E> exception=new Exception<>();
        exception.setCreateTime(new Date());
        exception.setHostName(getHostName());
        exception.setPath(webRequest.getDescription(false).substring(4));
        exception.setMessage(message);

        apiError.setException(exception);

        return apiError;

    }

    private String getHostName(){
        try{
            return InetAddress.getLocalHost().getHostName();
        }catch (UnknownHostException e){
            return e.getMessage();
        }
    }

}
