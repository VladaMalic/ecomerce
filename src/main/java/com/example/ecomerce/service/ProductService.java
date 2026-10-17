package com.example.ecomerce.service;

import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.model.Product;
import com.example.ecomerce.model.User;
import com.example.ecomerce.repository.ProductRepository;
import com.example.ecomerce.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {
    //CRUD

    private final ProductRepository productRep;

    public void createProduct(ProductDTO product) {
        Product toSave = new Product();
        toSave.setName(product.getName());
        toSave.setDescription(product.getDescription());
        toSave.setPrice(product.getPrice());
        toSave.setStock(product.getStock());
        toSave.setCategory_id(product.getCategory_id());
        toSave.setDate(new Timestamp(System.currentTimeMillis()));

        productRep.save(toSave);
    }

    public Product getProductById(Long id){
        return productRep.findById(id).orElse(null);
    }

    public void deleteProduct(Long id){
        productRep.deleteById(id);
    }

    public List<Product> getAllProducts(){
        return productRep.findAll();
    }

    public void updateProduct(Long id, ProductDTO productDto){
        Product current = getProductById(id);
        Objects.requireNonNull(current, "The product can not be null");
        current.setName(productDto.getName());
        current.setDescription(productDto.getDescription());
        current.setPrice(productDto.getPrice());
        current.setStock(productDto.getStock());
    }

}