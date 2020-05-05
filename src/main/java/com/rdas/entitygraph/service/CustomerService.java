package com.rdas.entitygraph.service;

import com.rdas.entitygraph.model.Customer;
import com.rdas.entitygraph.model.CustomerRelation;
import com.rdas.entitygraph.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Optional<Customer> getById(Integer id) {
        return Optional.ofNullable(customerRepository.getOne(id));
    }

    public Customer getCustomerWithGraphById(Integer id, CustomerRelation relation) {
        return customerRepository.findWithGraph(id, relation.getName());
    }

    public Customer addCustomer(Customer customer) {
        return customerRepository.saveAndFlush(customer);
    }
}
