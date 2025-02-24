package com.champ.ecomproductservice.advices;

import com.champ.ecomproductservice.dtos.ErrorDto;
import com.champ.ecomproductservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorDto> handleProductNotFoundException(ProductNotFoundException productNotFoundException){
        System.out.println(productNotFoundException.getMessage());
        ErrorDto errorDto = new ErrorDto();

        errorDto.setMessage(productNotFoundException.getMessage());

        ResponseEntity<ErrorDto> responseEntity = new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
        return responseEntity;
    }
}
