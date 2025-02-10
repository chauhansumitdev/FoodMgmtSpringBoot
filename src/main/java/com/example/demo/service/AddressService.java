package com.example.demo.service;

import com.example.demo.dto.AddressDTO;
import com.example.demo.entity.Address;
import com.example.demo.exception.AddressException;
import com.example.demo.repository.AddressRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final AddressRepository addressRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public AddressService(AddressRepository addressRepository, ModelMapper modelMapper) {
        this.addressRepository = addressRepository;
        this.modelMapper = modelMapper;
    }

    public AddressDTO updateAddress(Long id, AddressDTO addressDTO) {
        Optional<Address> existingAddress = addressRepository.findById(id);

        if (existingAddress.isPresent()) {
            Address address = existingAddress.get();
            address.setCity(addressDTO.getCity());
            address.setStreet(addressDTO.getStreet());
            addressRepository.save(address);
            return modelMapper.map(address, AddressDTO.class);
        } else {
            throw new AddressException("Address Does Not Exist");
        }
    }
}
