package com.bahri.spring5mvcrest.controller.v1;

import com.bahri.spring5mvcrest.api.v1.model.CustomerDto;
import com.bahri.spring5mvcrest.services.CustomerService;
import com.bahri.spring5mvcrest.services.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static com.bahri.spring5mvcrest.controller.v1.AbstractRestControllerTest.asJsonString;
import static com.bahri.spring5mvcrest.services.CustomerServiceImpl.API_V_1_CUSTOMERS;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new RestResponseEntityExceptionHandler())
                .build();

    }

    @Test
    void getAllCustomers() throws Exception {
        List<CustomerDto> customerDtoList = Arrays.asList(new CustomerDto(),new CustomerDto());

        when(customerService.getAllCustomers()).thenReturn(customerDtoList);

        mockMvc.perform(MockMvcRequestBuilders.get(API_V_1_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerByName() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Raed");

        when(customerService.getCustomerById(anyLong())).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.get(API_V_1_CUSTOMERS+"1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName",equalTo("Raed")));
    }

    @Test
    void createCustomer() throws Exception {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setFirstName("Raed");
        customerDto.setLastName("Bahri");

        when(customerService.createNewCustomer(any())).thenReturn(customerDto);

        mockMvc.perform(MockMvcRequestBuilders.post(API_V_1_CUSTOMERS)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(customerDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName",equalTo("Raed")));
    }

    @Test
    public void testUpdateCustomer() throws Exception {
        //given
        CustomerDto customer = new CustomerDto();
        customer.setFirstName("Fred");
        customer.setLastName("Flintstone");

        CustomerDto returnDTO = new CustomerDto();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName(customer.getLastName());
        returnDTO.setCustomerUrl(API_V_1_CUSTOMERS+"1");

        when(customerService.saveCustomerByDTO(anyLong(), any(CustomerDto.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(put("/api/v1/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", equalTo(API_V_1_CUSTOMERS+"1")));
    }

    @Test
    public void testPatchCustomer() throws Exception {
        //given
        CustomerDto customer = new CustomerDto();
        customer.setFirstName("Fred");

        CustomerDto returnDTO = new CustomerDto();
        returnDTO.setFirstName(customer.getFirstName());
        returnDTO.setLastName("Flintstone");
        returnDTO.setCustomerUrl(API_V_1_CUSTOMERS+"1");

        when(customerService.patchCustomer(anyLong(), any(CustomerDto.class))).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(patch(API_V_1_CUSTOMERS+"1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(customer)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName", equalTo("Fred")))
                .andExpect(jsonPath("$.lastName", equalTo("Flintstone")))
                .andExpect(jsonPath("$.customer_url", equalTo(API_V_1_CUSTOMERS+"1")));
    }

    @Test
    public void testDeleteCustomer() throws Exception {
        mockMvc.perform(delete(API_V_1_CUSTOMERS+"1")
                .contentType(MediaType.APPLICATION_JSON)
                ).andExpect(status().isOk());

        verify(customerService,times(1)).deleteCustomerById(anyLong());
    }

    @Test
    void TestCustomerNotFound() throws Exception {

        when(customerService.getCustomerById(anyLong())).thenThrow(ResourceNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get(API_V_1_CUSTOMERS+"11"))
                .andExpect(status().isNotFound());
    }

}