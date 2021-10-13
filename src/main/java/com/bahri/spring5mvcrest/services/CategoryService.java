package com.bahri.spring5mvcrest.services;

import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;

import java.util.List;

public interface CategoryService {

    List<CategoryDto> getAllCategories();

    CategoryDto getCategoryByName(String s);
}
