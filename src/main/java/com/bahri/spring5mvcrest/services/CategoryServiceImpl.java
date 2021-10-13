package com.bahri.spring5mvcrest.services;

import com.bahri.spring5mvcrest.api.v1.mapper.CategoryMapper;
import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(categoryMapper::categorytoCategoryDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryByName(String s) {
        return categoryMapper.categorytoCategoryDTO(categoryRepository.findByName(s));
    }
}
