package com.example.demo.controller;


import com.example.demo.dto.OrderDTO;
import com.example.demo.dto.OrderDTORequest;
import com.example.demo.dto.OrderDTOResponse;
import com.example.demo.entity.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/orders")
public class OrderController {


    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping()
    public ResponseEntity<OrderDTOResponse> createOrder(@RequestBody OrderDTORequest orderDTORequest){
        return new ResponseEntity<>(orderService.createOrder(orderDTORequest), HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<OrderDTO> updateStatus(@RequestBody OrderDTO orderDTO){
        return new ResponseEntity<>(orderService.updateOrder(orderDTO), HttpStatus.ACCEPTED);
    }


    @GetMapping("/{id}")
    public Page<Order> getOrdersByCustomer(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return orderService.getOrdersByCustomer(id, page, size);
    }

}
