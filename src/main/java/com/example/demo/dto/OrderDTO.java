package com.example.demo.dto;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Product;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class OrderDTO {

    private Long id;

    private Integer orderNumber;

    private String status;

    private Customer customer;

    @NotNull(message =  "Customer ID is required")
    private Long customerID;


    @NotNull(message = "Add at least one product to prepare order")
    private List<Long> productID;

    private List<Product> products;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
