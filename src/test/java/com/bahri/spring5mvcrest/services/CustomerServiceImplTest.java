package com.bahri.spring5mvcrest.services;

import com.bahri.spring5mvcrest.api.v1.mapper.CustomerMapper;
import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;
import com.bahri.spring5mvcrest.domain.Customer;
import com.bahri.spring5mvcrest.repositories.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class CustomerServiceImplTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Raed";
    CustomerService customerService;

    @Mock
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, CustomerMapper.INSTANCE);
    }

    @Test
    void getAllCustomers() {
        List<Customer> customerList = Arrays.asList(new Customer(), new Customer());
        when(customerRepository.findAll()).thenReturn(customerList);

        List<CustomerDto> customers  = customerService.getAllCustomers();

        assertEquals(customers.size(),2);


    }

    @Test
    void getCustomerById() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName("Bahri");

        when(customerRepository.findById(anyLong())).thenReturn(java.util.Optional.of(customer));

        CustomerDto customerDto = customerService.getCustomerById(ID);

        assertEquals(FIRST_NAME,customerDto.getFirstName());
    }

    @Test
    void createCustomer() {

        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);
        customer.setLastName("Bahri");

        when(customerRepository.save(any())).thenReturn(customer);
        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setFirstName(FIRST_NAME);
        customerDto1.setLastName("Bahri");
        CustomerDto customerDto = customerService.createNewCustomer(customerDto1);

        assertEquals(FIRST_NAME,customerDto.getFirstName());

    }
}