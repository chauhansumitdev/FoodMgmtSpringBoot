package com.example.demo.service;


import com.example.demo.dto.OrderDTO;
import com.example.demo.entity.Customer;
import com.example.demo.entity.Order;
import com.example.demo.entity.Product;
import com.example.demo.exception.CustomerException;
import com.example.demo.exception.OrderException;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.awt.font.TextHitInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {


    private ModelMapper mapper;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private ProductRepository productRepository;

    @Autowired
    public OrderService(ProductRepository productRepository, CustomerRepository customerRepository,ModelMapper mapper, OrderRepository orderRepository){
        this.mapper = mapper;
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    public OrderDTO createOrder(OrderDTO orderDTO){

        Long userID = orderDTO.getCustomerID();
        List<Long> productID = orderDTO.getProductID();

        Optional<Customer> existingCustomer = customerRepository.findById(userID);

        if(existingCustomer.isPresent()){

            Customer customer = existingCustomer.get();
            List<Product> products = new ArrayList<>();

            for(Long id : productID){

                Optional<Product> currentProduct = productRepository.findById(id);

                if(currentProduct.isPresent()){
                    products.add(currentProduct.get());
                }
            }

            Order order = new Order();
            order.setOrderNumber((int)(Math.random()*1000));
            order.setStatus("INITIATED");
            order.setCustomer(customer);
            order.setProducts(products);

            orderRepository.save(order);


            return mapper.map(order, OrderDTO.class);

        }else{
            throw new OrderException("Failed to create order");
        }

    }


    public OrderDTO updateOrder(OrderDTO orderDTO){

        Optional<Order> existingOrder = orderRepository.findById(orderDTO.getId());

        if(existingOrder.isPresent()){
            Order order = existingOrder.get();

            if(order.getStatus().equals("DELIVERED")){
                throw  new OrderException("ORDER ALREADY DELIVERED");
            }
            order.setStatus(orderDTO.getStatus());
            return mapper.map(orderRepository.save(order), OrderDTO.class);
        }else{
            throw new OrderException("ORDER DOES NOT EXIST");
        }
    }



    public Page<Order> getOrdersByCustomer(Long customerId, int page, int size) {
        Optional<Customer> customer = customerRepository.findById(customerId);

        if(!customer.isPresent()){
            throw new OrderException("Customer does not exit");
        }

        Pageable pageable = PageRequest.of(page, size);

        return orderRepository.findByCustomer(customer.get(), pageable);
    }


}
