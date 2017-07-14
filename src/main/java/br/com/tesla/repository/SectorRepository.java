package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.tesla.core.model.entities.Sector;

public interface SectorRepository extends JpaRepository<Sector, Integer>, JpaSpecificationExecutor<Sector> {

}
