package br.com.tesla.auth.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.tesla.auth.model.entities.Module;

public interface ModuleRepository extends CrudRepository<Module, Integer> {

}
