package br.com.tesla.financial.model.dto;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

public class DefaultSpecifications {
	
	public static <T> Specifications<T> mount(List<Specification<T>> list){
		Specifications<T> where = null;
		
		for(Specification<T> spec : list){
			if(where == null){
				where = Specifications.where(spec);
			}else{
				where = where.and(spec);
			}
		}
		
		return where;
	}

}
