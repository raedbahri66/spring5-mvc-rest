package com.bahri.spring5mvcrest.services;

import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;

import java.util.List;

public interface CustomerService {
    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(Long id);

    CustomerDto createNewCustomer(CustomerDto customerDto);

    CustomerDto saveCustomerByDTO(Long id, CustomerDto customerDTO);

    CustomerDto patchCustomer(Long id, CustomerDto customerDTO);

    void deleteCustomerById(Long id);

}
