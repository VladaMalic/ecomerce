package com.example.ecomerce.controller;

import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.dto.UserDTO;
import com.example.ecomerce.model.Product;
import com.example.ecomerce.model.User;
import com.example.ecomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    //interaction between user and service
    private final ProductService productService;

    @GetMapping("/products/all")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductAPI(@PathVariable(name = "id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProductAPI(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
    }

    @PostMapping("/products/add")
    public void createProduct(@RequestBody ProductDTO dto){
        productService.createProduct(dto);
    }

    @PostMapping("/products/update")
    public void updateProduct(@PathVariable(name = "id") Long id, @RequestBody ProductDTO dto){
        productService.updateProduct(id,dto);
    }


}