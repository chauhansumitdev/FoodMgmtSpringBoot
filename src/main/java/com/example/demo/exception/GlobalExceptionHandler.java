package com.example.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AddressException.class)
    public ResponseEntity<CustomErrorResponse> handleAddressException(RuntimeException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), 400);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomerException.class)
    public ResponseEntity<CustomErrorResponse> handleCustomerException(RuntimeException e) {
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), 400);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ProductException.class)
    public ResponseEntity<CustomErrorResponse> handleProductException(RuntimeException e){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), 400);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderException.class)
    public ResponseEntity<CustomErrorResponse> handleOrderException(RuntimeException e){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(e.getMessage(), 400);
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

}
