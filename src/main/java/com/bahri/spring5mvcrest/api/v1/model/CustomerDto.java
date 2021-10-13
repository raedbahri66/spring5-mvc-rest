package com.bahri.spring5mvcrest.api.v1.model;

import lombok.Data;

@Data
public class CustomerDto {
    private String firstName;
    private String lastName;
    private String customerUrl;
}
