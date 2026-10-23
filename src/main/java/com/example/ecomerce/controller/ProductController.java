package com.example.ecomerce.controller;

import com.example.ecomerce.dto.ProductDTO;
import com.example.ecomerce.model.Product;
import com.example.ecomerce.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Products", description = "Managing products")
public class ProductController {

    //interaction between user and service
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/all")
    @Operation(summary = "List of all products")
    @ApiResponse(responseCode = "200", description = "List returned successfully")
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    @GetMapping("/products/{id}")
    public Product getProductAPI(@PathVariable(name = "id") Long id){
        return productService.getProductById(id);
    }

    @DeleteMapping("/products/delete/{id}")
    @Operation(summary = "Deletes a product", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "The product was deleted")
    @ApiResponse(responseCode = "404", description = "The product does not exist")
    public void deleteProductAPI(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
    }

    @PostMapping("/products/add")
    @Operation(summary = "Creates a new product", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "The new product was created")
    @ApiResponse(responseCode = "401", description = "Not authenticated")
    public void createProduct(@RequestBody ProductDTO dto){
        productService.createProduct(dto);
    }

    @PutMapping("/products/{id}")
    @Operation(summary = "Updates a product", security = @SecurityRequirement(name = "basicAuth"))
    @ApiResponse(responseCode = "200", description = "The product was updated")
    @ApiResponse(responseCode = "404", description = "The product does not exist")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductDTO dto) {
        productService.updateProduct(id, dto);
    }


}
