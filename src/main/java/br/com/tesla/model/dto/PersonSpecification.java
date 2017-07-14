package br.com.tesla.model.dto;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.core.model.entities.Person;
import br.com.tesla.core.model.entities.PersonType;

public class PersonSpecification {

	public static Specification<Person> name(String name) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(cb.upper(root.<String> get("name")), "%"+name.toUpperCase()+"%");
			}
		};
	}
	
	public static Specification<Person> fantasy(String fantasy) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.like(cb.upper(root.<String> get("fantasy")), "%"+fantasy.toUpperCase()+"%");
			}
		};
	}
	
	public static Specification<Person> personType(PersonType personType) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("personType"), personType);
			}
		};
	}
	
	public static Specification<Person> cpf(String cpf) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("cpf"), cpf);
			}
		};
	}
	
	public static Specification<Person> rg(String rg) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("rg"), rg);
			}
		};
	}
	
	public static Specification<Person> cnpj(String cnpj) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("cnpj"), cnpj);
			}
		};
	}
	
	public static Specification<Person> ie(String ie) {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String> get("ie"), ie);
			}
		};
	}
	
	public static Specification<Person> isCustomer() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get("customer").get("enabled"), true);
			}
		};
	}
	
	public static Specification<Person> isUser() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get("user").get("enabled"), true);
			}
		};
	}
	
	public static Specification<Person> isEmployee() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get("employee").get("enabled"), true);
			}
		};
	}
	
	public static Specification<Person> isProvider() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get("provider").get("enabled"), true);
			}
		};
	}
	
	public static Specification<Person> isWorkgroup() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get("workgroup").get("enabled"), true);
			}
		};
	}
	
	public static Specification<Person> isBroker() {
		return new Specification<Person>() {
			@Override
			public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Boolean> get("broker").get("enabled"), true);
			}
		};
	}
	
}
