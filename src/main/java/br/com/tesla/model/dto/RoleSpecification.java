package br.com.tesla.model.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.core.model.entities.Role;

public class RoleSpecification {

	public static Specification<Role> name(String name) {
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(cb.upper(root.<String> get("name")), "%"+name.toUpperCase()+"%");
			}
		};
	}

	public static Specification<Role> master(Integer roleId) {
		return new Specification<Role>() {
			@Override
			public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer> get("master").get("id"), roleId);
			}
		};
	}	
	
}