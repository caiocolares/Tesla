package br.com.tesla.auth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.tesla.auth.model.entities.Module;
import br.com.tesla.auth.model.entities.ModuleResource;

public interface ModuleResourceRepository extends CrudRepository<ModuleResource, Integer> {

	List<ModuleResource> findByModule(Module module);
	
}
