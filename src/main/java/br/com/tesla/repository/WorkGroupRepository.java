package br.com.tesla.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.tesla.core.model.entities.WorkGroup;
import br.com.tesla.core.model.entities.WorkGroupType;

public interface WorkGroupRepository extends JpaRepository<WorkGroup, String> {
		
	List<WorkGroup> findByWorkgroupType(WorkGroupType workgroupType);
	
}