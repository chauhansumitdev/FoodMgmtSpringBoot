package com.example.demo.service;

import com.example.demo.dto.ProductDTO;
import com.example.demo.entity.Product;
import com.example.demo.exception.ProductException;
import com.example.demo.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ProductService {


    private ModelMapper mapper;
    private ProductRepository productRepository;

    @Autowired
    public ProductService(ModelMapper mapper, ProductRepository productRepository){
        this.mapper = mapper;
        this.productRepository = productRepository;
    }


    public ProductDTO createProduct(ProductDTO productDTO){
        Product product = mapper.map(productDTO, Product.class);
        return mapper.map(productRepository.save(product),ProductDTO.class);

    }


    public ProductDTO getProduct(Long id){
        Optional<Product> existingProduct = productRepository.findById(id);

        if(existingProduct.isPresent()){
            return mapper.map(existingProduct.get(), ProductDTO.class);
        }else{
            throw new ProductException("Product Not Found");
        }
    }


    public ProductDTO updateProduct(Long id, ProductDTO productDTO){
        Optional<Product> product = productRepository.findById(id);
        productDTO.setId(id);
        if(product.isPresent()){
            Product existingProduct = product.get();
            existingProduct.setName(productDTO.getName());
            existingProduct.setPrice(productDTO.getPrice());
            return mapper.map(productRepository.save(existingProduct), ProductDTO.class);
        }else{
            throw  new ProductException("Product does not exist");
        }
    }


    public void deleteProduct(Long id){
        productRepository.deleteById(id);
    }


}
