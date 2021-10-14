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

import static com.bahri.spring5mvcrest.services.CustomerServiceImpl.API_V_1_CUSTOMERS;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

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

    @Test
    public void saveCustomerByDTO() throws Exception {

        //given
        CustomerDto customerDTO = new CustomerDto();
        customerDTO.setFirstName("Jim");

        Customer savedCustomer = new Customer();
        savedCustomer.setFirstName(customerDTO.getFirstName());
        savedCustomer.setLastName(customerDTO.getLastName());
        savedCustomer.setId(1L);

        when(customerRepository.save(any(Customer.class))).thenReturn(savedCustomer);

        //when
        CustomerDto savedDto = customerService.saveCustomerByDTO(1L, customerDTO);

        //then
        assertEquals(customerDTO.getFirstName(), savedDto.getFirstName());
        assertEquals(API_V_1_CUSTOMERS+"1", savedDto.getCustomerUrl());
    }

    @Test
    void deleteCustomerById() {
        Long Id = 1L;
        customerService.deleteCustomerById(Id);

        verify(customerRepository,times(1)).deleteById(anyLong());
    }
}