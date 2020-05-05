package com.rdas.entitygraph.resources;

import com.rdas.entitygraph.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdas.entitygraph.model.Customer;
import com.rdas.entitygraph.repository.CustomerRepository;
import com.rdas.entitygraph.resources.CustomerController;
import com.rdas.entitygraph.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CustomerController controller;

    @MockBean
    CustomerService customerService;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    public void initBeans() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(controller);
    }

    @BeforeEach
    public void init() {
    }

    @DisplayName("Get a known Customer with an ID")
    @Test
    public void shouldReturnACustomers() throws Exception {
//        CustomerDto customerDto = CustomerDto.builder().customerName("Alberto Giribaldi").build();

        Customer customer = Customer.builder().customerNumber(103).contactFirstName("Alberto").contactLastName("Giribaldi").build();
        when(customerService.getById(103)).thenReturn(Optional.of(customer));
        this.mockMvc.perform(get("/customers/103"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("103")))
                //.andExpect(content().string(containsString("Alberto Giribaldi")))
        ;
        //verifyNoMoreInteractions(customerService);
    }

    @DisplayName("Get a unknown Customer with an ID")
    @Test
    public void shouldReturnAVoidCustomers() throws Exception {
        mockMvc.perform(get("/customers/1"))
                .andDo(print()).andExpect(status().isOk())
        ;
    }

    @DisplayName("Create a customer by passing name")
    @Test
    public void createEmployeeAPI() throws Exception {
        Customer customer = Customer.builder()
                .customerNumber(103).contactFirstName("Alberto").contactLastName("Giribaldi").phone("1001").city("Dublin 12").postalCode("12").country("IE").creditLimit(Float.parseFloat("100"))
                .build();
        when(customerService.addCustomer(any())).thenReturn(customer);
        when(customerRepository.saveAndFlush(any())).thenReturn(customer);

        mockMvc.perform( MockMvcRequestBuilders
                .post("/customers")
                .content(asJsonString(new CustomerDto(null, "Alberto Giribaldi", "1001", "Dublin 12","12", "IE", 100)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.customerNumber").exists());
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}