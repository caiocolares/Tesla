package br.com.tesla.model.dto;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.core.model.entities.Person;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class PersonEntitie {
	private String name;
	private Specification<Person> specification;
	private boolean enabled;	
}
