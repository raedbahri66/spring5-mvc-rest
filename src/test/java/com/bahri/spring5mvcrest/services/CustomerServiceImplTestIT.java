package com.bahri.spring5mvcrest.services;

import com.bahri.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;
import com.bahri.spring5mvcrest.bootstrap.Bootstrap;
import com.bahri.spring5mvcrest.domain.Customer;
import com.bahri.spring5mvcrest.repositories.CategoryRepository;
import com.bahri.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerServiceImplTestIT {

    public static final String FIRST_NAME = "Jihed";
    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CategoryRepository categoryRepository;

    CustomerService customerService;

    @BeforeEach
    void setUp() throws Exception {

        Bootstrap bootstrap = new Bootstrap(categoryRepository,customerRepository);
        bootstrap.run();
        customerService= new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void patchCustomerUpdateFirstName() {
        long id = getFirstCustomerId();

        Customer customer = customerRepository.getById(id);
        assertNotNull(customer);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName(FIRST_NAME);

        customerService.patchCustomer(id,customerDto);

        Customer updatedCustomer = customerRepository.getById(id);

        assertEquals(FIRST_NAME,updatedCustomer.getFirstName());
    }



    Long getFirstCustomerId() {

        List<Customer> customerList = customerRepository.findAll();
        return customerList.get(1).getId();
    }
}