package com.example.ecomerce.controller;

import com.example.ecomerce.dto.CategoryDTO;
import com.example.ecomerce.model.Category;
import com.example.ecomerce.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
@Tag(name = "Categories", description = "Managing product categories")
public class CategoryController {

    private final CategoryService service;

    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List of all categories")
    @ApiResponse(responseCode = "200", description = "List returned successfully")
    public List<Category> getAll() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Creates a new category", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "The new category was created")
    @ApiResponse(responseCode = "400", description = "The name is empty")
    @ApiResponse(responseCode = "401", description = "Not authenticated")
    @ApiResponse(responseCode = "409", description = "Category already exists")
    public Category create(@RequestBody CategoryDTO dto) {
        return service.createCategory(dto);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return service.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Deletes a category", security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "The category was deleted")
    @ApiResponse(responseCode = "404", description = "The category does not exist")
    public void delete(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}