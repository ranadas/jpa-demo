package com.diee.jpademo.controller;

import com.diee.jpademo.dto.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

    @Test
    public void initBeans() {
        assertNotNull(mockMvc);
        assertNotNull(objectMapper);
        assertNotNull(controller);
    }

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/customers/"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, World")));
    }

    @DisplayName("Get a known Customer with an ID")
    @Test
    public void shouldReturnACustomers() throws Exception {
        this.mockMvc.perform(get("/customers/103"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Atelier graphique")))
        ;
    }

    @Disabled
    @DisplayName("Get a unknown Customer with an ID")
    @Test
    public void shouldReturnAVoidCustomers() throws Exception {
        mockMvc.perform(get("/customers/1"))
                .andDo(print()).andExpect(status().isOk())
        ;
    }

    @DisplayName("")
    @Test
    public void createEmployeeAPI() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/customers")
                .content(asJsonString(new CustomerDto(null, "custName4", "1001", "Dublin 12","12", "IE", 100)))
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