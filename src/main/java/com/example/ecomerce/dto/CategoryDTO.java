package com.example.ecomerce.dto;

import lombok.NoArgsConstructor;


@NoArgsConstructor
public class CategoryDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

