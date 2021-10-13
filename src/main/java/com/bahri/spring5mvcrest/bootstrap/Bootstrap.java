package com.bahri.spring5mvcrest.bootstrap;

import com.bahri.spring5mvcrest.domain.Category;
import com.bahri.spring5mvcrest.domain.Customer;
import com.bahri.spring5mvcrest.repositories.CategoryRepository;
import com.bahri.spring5mvcrest.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Category fruits = new Category();
        fruits.setName("Fruits");
        categoryRepository.save(fruits);

        Category fresh = new Category();
        fresh.setName("fresh");
        categoryRepository.save(fresh);

        Customer customer1 = new Customer();
        customer1.setFirstName("Raed");
        customer1.setLastName("Bahri");
        customerRepository.save(customer1);

        Customer customer = new Customer();
        customer.setFirstName("Ahmed");
        customer.setLastName("Dridi");
        customerRepository.save(customer);



    }
}
