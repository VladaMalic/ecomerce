package com.example.ecomerce.service;

import com.example.ecomerce.dto.CategoryDTO;
import com.example.ecomerce.model.Category;
import com.example.ecomerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRep;

    public Category createCategory(CategoryDTO dto) {
        String name = validateName(dto);
        if (categoryRep.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Categoria exista deja: " + name);
        }
        Category category = new Category();
        category.setName(name);
        return categoryRep.save(category);
    }

    public List<Category> getAllCategories() {
        return categoryRep.findAll();
    }

    public Category getCategoryById(Long id) {
        return categoryRep.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria nu exista: " + id));
    }

    public Category updateCategory(Long id, CategoryDTO dto) {
        Category category = getCategoryById(id);
        String name = validateName(dto);
        if (!category.getName().equals(name) && categoryRep.existsByName(name)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Categoria exista deja: " + name);
        }
        category.setName(name);
        return categoryRep.save(category);
    }

    public void deleteCategory(Long id) {
        getCategoryById(id); // arunca 404 daca nu exista
        categoryRep.deleteById(id);
    }

    private String validateName(CategoryDTO dto) {
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Numele categoriei este obligatoriu");
        }
        return dto.getName().trim();
    }
}