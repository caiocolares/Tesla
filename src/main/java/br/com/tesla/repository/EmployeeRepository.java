package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tesla.core.model.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
		
}