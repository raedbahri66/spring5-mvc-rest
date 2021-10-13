package com.bahri.spring5mvcrest.api.v1.mapper;

import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.domain.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryMapperTest {

    public static final String NAME = "Raed";
    public static final long ID = 1L;

    CategoryMapper categoryMapper = CategoryMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void categorytoCategoryDTO() throws Exception{
        Category category = new Category();
        category.setName(NAME);
        category.setId(ID);

        CategoryDto categoryDto = categoryMapper.categorytoCategoryDTO(category);

        assertEquals(ID,categoryDto.getId());
        assertEquals(NAME,categoryDto.getName());
    }
}