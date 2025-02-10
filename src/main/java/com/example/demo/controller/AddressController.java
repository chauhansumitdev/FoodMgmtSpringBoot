package com.example.demo.controller;


import com.example.demo.dto.AddressDTO;
import com.example.demo.entity.Address;
import com.example.demo.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/v1/addresses")
public class AddressController {


    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @PutMapping("/{id}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO){
        return new ResponseEntity<>(addressService.updateAddress(id, addressDTO), HttpStatus.ACCEPTED);
    }

}
