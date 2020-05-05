package com.rdas.entitygraph.resources;

import com.rdas.entitygraph.model.Customer;
import com.rdas.entitygraph.model.CustomerRelation;
import com.rdas.entitygraph.service.CustomerService;
import com.rdas.entitygraph.dto.*;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final ModelMapper modelMapper;

    public CustomerController(CustomerService customerService, ModelMapper modelMapper) {
        this.customerService = customerService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public CustomerDto getCustomer(@PathVariable("id") Integer id) {
        Optional<Customer> customerOptional = customerService.getById(id);
        return customerOptional
                .map(value -> modelMapper.map(value, CustomerDto.class))
                .orElseGet(() -> new CustomerDto());
    }

    @GetMapping("/{id}/with-orders")
    public CustomerWithOrdersDto getCustomerAndOrders(@PathVariable("id") Integer id) {

        Customer customer = customerService.getCustomerWithGraphById(id, CustomerRelation.CUSTOMER_WITH_ORDERS);
        CustomerWithOrdersDto customerWithOrders = modelMapper.map(customer, CustomerWithOrdersDto.class);
        List<OrderDto> orders = customer.getOrders().stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
        customerWithOrders.setOrders(orders);
        return customerWithOrders;
    }

    @GetMapping("/{id}/with-orders-and-details")
    public CustomerWithOrdersDto getCustomerWithOrdersAndDetails(@PathVariable("id") Integer id) {

        Customer customer = customerService.getCustomerWithGraphById(id, CustomerRelation.CUSTOMER_WITH_ORDERS_AND_DETAILS);
        CustomerWithOrdersDto customerDto = modelMapper.map(customer, CustomerWithOrdersDto.class);
        List<OrderDto> orders = customer.getOrders().stream()
                .map(order -> {
                    OrderWithDetailDto orderWithDetails = modelMapper.map(order, OrderWithDetailDto.class);
                    List<OrderDetailDto> orderDetails = order.getOrderDetail().stream().map(orderDetail -> {
                        OrderDetailDto orderDetailDto = new OrderDetailDto();
                        orderDetailDto.setOrderLineNumber(orderDetail.getOrderLineNumber());
                        orderDetailDto.setPriceEach(orderDetail.getPriceEach());
                        orderDetailDto.setProductCode(orderDetail.getProduct().getProductCode());
                        orderDetailDto.setQuantityOrdered(orderDetail.getQuantityOrdered());
                        return orderDetailDto;
                    }).collect(Collectors.toList());

                    orderWithDetails.setOrderDetails(orderDetails);
                    return orderWithDetails;
                }).collect(Collectors.toList());
        customerDto.setOrders(orders);
        return customerDto;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addCustomer(@RequestBody CustomerDto customerDto) {
        Customer customer = modelMapper.map(customerDto, Customer.class);
        Customer customer1 = customerService.addCustomer(customer);
        return modelMapper.map(customer1, CustomerDto.class);
    }
}
