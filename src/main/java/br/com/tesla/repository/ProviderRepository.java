package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tesla.core.model.entities.Provider;

public interface ProviderRepository extends JpaRepository<Provider, String> {
		
}