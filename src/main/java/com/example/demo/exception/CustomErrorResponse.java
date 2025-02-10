package com.example.demo.exception;


import lombok.Data;

@Data
public class CustomErrorResponse {

    private int statusCode;
    private String message;

    public CustomErrorResponse(String message, int statusCode){
        this.statusCode = statusCode;
        this.message = message;
    }
}
