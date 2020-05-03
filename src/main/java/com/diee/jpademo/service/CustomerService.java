package com.diee.jpademo.service;

import com.diee.jpademo.model.Customer;
import com.diee.jpademo.model.CustomerRelation;
import com.diee.jpademo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return customerRepository.save(customer);
    }
}
