package com.example.ecomerce.controller;

import com.example.ecomerce.dto.CategoryDTO;
import com.example.ecomerce.model.Category;
import com.example.ecomerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService service;

    @GetMapping
    public List<Category> getAll() {
        return service.getAllCategories();
    }

    @GetMapping("/{id}")
    public Category getById(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Category create(@RequestBody CategoryDTO dto) {
        return service.createCategory(dto);
    }

    @PutMapping("/{id}")
    public Category update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        return service.updateCategory(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.deleteCategory(id);
    }
}