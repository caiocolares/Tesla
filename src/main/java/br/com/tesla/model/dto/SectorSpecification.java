package br.com.tesla.model.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.core.model.entities.Sector;

public class SectorSpecification {

	public static Specification<Sector> name(String name) {
		return new Specification<Sector>() {
			@Override
			public Predicate toPredicate(Root<Sector> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(cb.upper(root.<String> get("name")), "%"+name.toUpperCase()+"%");
			}
		};
	}

	public static Specification<Sector> workgroup(String workgroupId) {
		return new Specification<Sector>() {
			@Override
			public Predicate toPredicate(Root<Sector> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("workgroup").get("id"), workgroupId);
			}
		};
	}
	
	public static Specification<Sector> headOffice(Integer sectorId) {
		return new Specification<Sector>() {
			@Override
			public Predicate toPredicate(Root<Sector> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer> get("headOffice").get("id"), sectorId);
			}
		};
	}
	
}
