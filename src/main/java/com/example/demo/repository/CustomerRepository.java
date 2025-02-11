package com.example.demo.repository;

import com.example.demo.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {


    Page<Customer> findByFirstNameContainingIgnoreCaseAndEmailContainingIgnoreCase(String firstName, String email, Pageable pageable);


}
