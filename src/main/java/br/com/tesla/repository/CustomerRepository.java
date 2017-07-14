package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tesla.core.model.entities.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
		
}