package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tesla.core.model.entities.Broker;

public interface BrokerRepository extends JpaRepository<Broker, String> {
		
}