package org.learning.springilmiofotoalbum.exception;

import java.util.function.Supplier;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException(){
        super();
    }
    public CategoryNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
