package com.bahri.spring5mvcrest.api.v1.model;


import lombok.Data;

import java.util.List;

@Data
public class CategoryListDto {

    List<CategoryDto> categories;

    public CategoryListDto(List<CategoryDto> categories) {
        this.categories = categories;
    }
}
