package com.bahri.spring5mvcrest.api.v1.mapper;

import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.domain.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "id", target = "id")
    CategoryDto categorytoCategoryDTO(Category category);
}
