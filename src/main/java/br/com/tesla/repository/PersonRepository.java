package br.com.tesla.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.tesla.core.model.entities.Person;

public interface PersonRepository extends JpaRepository<Person, String>, JpaSpecificationExecutor<Person> {

	@Query("select p from Person p left join fetch p.phones f join fetch p.user u where u.login = :login ")
	Person findByLogin(@Param("login") String login);
	
	Person findByEmail(String email);
	
	Person findByCnpj(String cnpj);
	
}