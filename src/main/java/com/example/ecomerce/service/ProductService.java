package com.example.ecomerce.service;

import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.model.Product;
import com.example.ecomerce.repository.ProductRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.util.List;

@Service

public class ProductService {
    //CRUD

    private final ProductRepository productRep;

    public ProductService(ProductRepository productRep) {
        this.productRep = productRep;
    }
    @CacheEvict(value = "products", allEntries = true)
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

    public Product getProductById(Long id) {
        return productRep.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found: " + id));
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRep.delete(product);
    }

    @Cacheable("products")
    public List<Product> getAllProducts(){
        return productRep.findAll();
    }

    @CacheEvict(value = "products", allEntries = true)
    public void updateProduct(Long id, ProductDTO productDto) {
        Product current = getProductById(id);
        current.setName(productDto.getName());
        current.setDescription(productDto.getDescription());
        current.setPrice(productDto.getPrice());
        current.setStock(productDto.getStock());
        current.setCategory_id(productDto.getCategory_id());
        productRep.save(current);
    }

}
