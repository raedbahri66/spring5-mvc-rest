package com.bahri.spring5mvcrest.api.v1.model;

import lombok.Data;

import java.util.List;

@Data
public class CustomerListDto {

    List<CustomerDto> customers;

    public CustomerListDto(List<CustomerDto> customers) {
        this.customers = customers;
    }
}
