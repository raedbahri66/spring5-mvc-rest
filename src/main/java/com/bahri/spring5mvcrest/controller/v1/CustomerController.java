package com.bahri.spring5mvcrest.controller.v1;


import com.bahri.spring5mvcrest.api.v1.model.CategoryDto;
import com.bahri.spring5mvcrest.api.v1.model.CategoryListDto;
import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;
import com.bahri.spring5mvcrest.api.v1.model.CustomerListDto;
import com.bahri.spring5mvcrest.repositories.CustomerRepository;
import com.bahri.spring5mvcrest.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<CustomerListDto> getAllCustomers() {
        return new ResponseEntity<CustomerListDto>(new CustomerListDto(
                customerService.getAllCustomers()), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerByName(@PathVariable Long id) {
        return new ResponseEntity<CustomerDto>(customerService.getCustomerById(id),HttpStatus.OK);
    }
}
