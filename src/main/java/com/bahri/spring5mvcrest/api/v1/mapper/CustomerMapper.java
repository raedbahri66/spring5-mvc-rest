package com.bahri.spring5mvcrest.api.v1.mapper;

import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;
import com.bahri.spring5mvcrest.domain.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    CustomerDto customerToCustomerDto(Customer customer);
}
