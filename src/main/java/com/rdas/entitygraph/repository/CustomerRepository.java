package com.rdas.entitygraph.repository;

import com.rdas.entitygraph.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer>, BaseRepository<Customer, Integer> {

}
