package com.champ.ecomproductservice.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) {
        super(msg);
    }
}
