package com.bahri.spring5mvcrest.bootstrap;

import com.bahri.spring5mvcrest.domain.Category;
import com.bahri.spring5mvcrest.repositories.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    public Bootstrap(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");
        categoryRepository.save(fruits);

        Category fresh = new Category();
        fresh.setName("fresh");
        categoryRepository.save(fresh);
        System.out.println(String.valueOf(categoryRepository.findAll().size()));
    }
}
