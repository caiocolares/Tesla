package br.com.tesla.model.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.core.model.entities.Lotation;

public class LotationSpecification {

	public static Specification<Lotation> person(String personId) {
		return new Specification<Lotation>() {
			@Override
			public Predicate toPredicate(Root<Lotation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer> get("person").get("id"), personId);
			}
		};
	}

	public static Specification<Lotation> sector(Integer sectorId) {
		return new Specification<Lotation>() {
			@Override
			public Predicate toPredicate(Root<Lotation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer> get("sector").get("id"), sectorId);
			}
		};
	}
	
	public static Specification<Lotation> role(Integer roleId) {
		return new Specification<Lotation>() {
			@Override
			public Predicate toPredicate(Root<Lotation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer> get("role").get("id"), roleId);
			}
		};
	}	
	
}