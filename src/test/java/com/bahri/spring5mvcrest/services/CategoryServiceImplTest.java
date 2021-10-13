package com.bahri.spring5mvcrest.services;

import com.bahri.spring5mvcrest.api.v1.mapper.CategoryMapper;
import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.domain.Category;
import com.bahri.spring5mvcrest.repositories.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class CategoryServiceImplTest {

    public static final String NAME = "Raed";
    public static final long ID = 1L;
    CategoryService categoryService;

    @Mock
    CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        categoryService = new CategoryServiceImpl(categoryRepository, CategoryMapper.INSTANCE);
    }

    @Test
    void getAllCategories() {

        //given
        List<Category> list = Arrays.asList(new Category(),new Category());
        when(categoryRepository.findAll()).thenReturn(list);

        //when
        List<CategoryDto> categoryDtoList = categoryService.getAllCategories();

        //then
        assertEquals(2,categoryDtoList.size());
    }

    @Test
    void getCategoryByName() {

        Category category = new Category();
        category.setId(ID);
        category.setName(NAME);
        when(categoryRepository.findByName(anyString())).thenReturn(category);

        CategoryDto categoryDto = categoryService.getCategoryByName(NAME);

        assertEquals(NAME,categoryDto.getName());
    }
}