package com.bahri.spring5mvcrest.controller.v1;


import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.api.v1.model.CategoryListDto;
import com.bahri.spring5mvcrest.services.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/categories/")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<CategoryListDto> getAllCategories() {
        return new ResponseEntity<CategoryListDto>(new CategoryListDto(
                categoryService.getAllCategories()), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<CategoryDto> getCategoryByName(@PathVariable String name) {
        return new ResponseEntity<CategoryDto>(categoryService.getCategoryByName(name),HttpStatus.OK);
    }
}
