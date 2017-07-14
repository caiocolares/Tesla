package br.com.tesla.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import br.com.tesla.auth.model.entities.User;

@RepositoryRestResource(path="user")
public interface UserRepository extends JpaRepository<User, String> {

	User findByLogin(String login);
	
	//@Query("select p from Person p left join fetch p.phones f join fetch p.user u where u.login = :login ")
	@Query("select u from User u join u.person p where u.active = :active AND ( u.login like :login OR p.name like :name )")
	Page<User> findAllByActiveAndPersonNameOrLogin(@Param("active") Boolean active, @Param("name") String name, @Param("login") String login, Pageable page);
	
	@Query("select p from Person p join p.user u where u.active = :active AND ( u.login like :login OR p.name like :name )")
	Page<User> findAllByActiveAndPersonNameOrLogin2(@Param("active") Boolean active, @Param("name") String name, @Param("login") String login, Pageable page);
	
	//Page<User> findByActiveAndLoginIgnoreCaseContaining(Boolean active, String login, Pageable page);
}
