package br.com.tesla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tesla.core.model.entities.City;
import br.com.tesla.core.model.entities.UF;

public interface CityRepository extends JpaRepository<City, Integer> {

	List<City> findByUf(UF uf);
	
}