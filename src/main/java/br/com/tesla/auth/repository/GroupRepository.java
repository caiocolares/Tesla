package br.com.tesla.auth.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.tesla.auth.model.entities.Group;

public interface GroupRepository extends CrudRepository<Group, Integer> {

}
