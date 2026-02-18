package com.metehansargin.springlearn2.exception;

public class BaseException extends RuntimeException{

    public BaseException(){

    }

    public BaseException(ErrorMesage errorMesage ){
        super(errorMesage.prepareErrorMessage());
    }
}
