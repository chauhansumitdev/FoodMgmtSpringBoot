package com.example.demo.dto;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDTORequest {

    @NotNull(message =  "Customer ID is required")
    private Long customerID;

    @NotNull(message = "Add at least one product to prepare order")
    private List<Long> productID;

    public Long getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Long customerID) {
        this.customerID = customerID;
    }

    public List<Long> getProductID() {
        return productID;
    }

    public void setProductID(List<Long> productID) {
        this.productID = productID;
    }
}
