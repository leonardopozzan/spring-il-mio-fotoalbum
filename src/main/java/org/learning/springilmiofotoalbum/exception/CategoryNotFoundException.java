package org.learning.springilmiofotoalbum.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(){
        super();
    }
    public CategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
