package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.tesla.core.model.entities.Lotation;

public interface LotationRepository extends JpaRepository<Lotation, Integer>, JpaSpecificationExecutor<Lotation> {

}
