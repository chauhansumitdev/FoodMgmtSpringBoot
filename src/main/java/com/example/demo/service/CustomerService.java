package com.example.demo.service;


import com.example.demo.dto.CustomerDTO;
import com.example.demo.entity.Customer;
import com.example.demo.exception.CustomerException;
import com.example.demo.repository.CustomerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {


    private ModelMapper mapper;
    private CustomerRepository customerRepository;



    @Autowired
    public CustomerService(ModelMapper mapper, CustomerRepository customerRepository){
        this.customerRepository = customerRepository;
        this.mapper = mapper;
    }



    public CustomerDTO createCustomer(CustomerDTO customerDTO){
        Customer customer = mapper.map(customerDTO, Customer.class);
        customerRepository.save(customer);
        return mapper.map(customer, CustomerDTO.class);
    }




    public CustomerDTO  getCustomer(Long id){
        Optional<Customer> customer = customerRepository.findById(id);

        if(customer.isPresent()){
            return mapper.map(customer.get(), CustomerDTO.class);
        }else{
            throw  new CustomerException("Customer does not exist");
        }
    }




    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO){
        Optional<Customer> customer = customerRepository.findById(id);
        customerDTO.setId(id); // because this was throwing errors while mapping
        if(customer.isPresent()){
            customerDTO.setAddress(customer.get().getAddress());
            Customer existingCustomer = customer.get();
            mapper.map(customerDTO, existingCustomer);
            customerRepository.save(existingCustomer);
            return mapper.map(existingCustomer, CustomerDTO.class);
        }else{
            throw  new CustomerException("Customer does not exist");
        }
    }



    public void deleteCustomer(Long id){
        customerRepository.deleteById(id);
    }

    public Page<Customer> getCustomers(String firstName, String email, int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return customerRepository.findByFirstNameContainingIgnoreCaseAndEmailContainingIgnoreCase(firstName, email, pageable);
    }

}
