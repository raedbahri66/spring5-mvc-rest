package com.bahri.spring5mvcrest.api.v1.mapper;

import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;
import com.bahri.spring5mvcrest.domain.Customer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    public static final long ID = 1L;
    public static final String FIRST_NAME = "Raed";
    CustomerMapper customerMapper = CustomerMapper.INSTANCE;
    @Test
    void customerToCustomerDto() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setFirstName(FIRST_NAME);

        CustomerDto customerDto = customerMapper.customerToCustomerDto(customer);

        assertEquals(FIRST_NAME,customerDto.getFirstName());
    }
}