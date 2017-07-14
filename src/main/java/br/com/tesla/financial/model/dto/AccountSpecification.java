package br.com.tesla.financial.model.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.financial.model.entities.Account;

public class AccountSpecification {

	public static Specification<Account> name(String name) {
		return new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(cb.upper(root.<String> get("name")), "%"+name.toUpperCase()+"%");
			}
		};
	}

	public static Specification<Account> workgroup(String workgroupId) {
		return new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("workgroup").get("id"), workgroupId);
			}
		};
	}
	
	public static Specification<Account> bank(Integer bankId) {
		return new Specification<Account>() {
			@Override
			public Predicate toPredicate(Root<Account> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("bankAccount").get("id").get("bank"), bankId);
			}
		};
	}

}
