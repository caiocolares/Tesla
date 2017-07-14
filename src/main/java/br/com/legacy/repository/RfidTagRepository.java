package br.com.legacy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.legacy.model.entities.RfidTag;

public interface RfidTagRepository extends JpaRepository<RfidTag, String> {

	List<RfidTag> findById(String id);
	
}
