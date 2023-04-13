package org.learning.springilmiofotoalbum.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException(String errorMessage){
        super(errorMessage);
    }
    public ImageNotFoundException(){
        super();
    }
}
