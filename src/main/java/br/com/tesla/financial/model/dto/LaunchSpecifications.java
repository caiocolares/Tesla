package br.com.tesla.financial.model.dto;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import br.com.tesla.financial.model.entities.Launch;
import br.com.tesla.financial.model.entities.LaunchType;

public class LaunchSpecifications  {
	
	public static Specification<Launch> type(LaunchType type){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<LaunchType>get("type"), type);
			}
		};
	}
	
	public static Specification<Launch> workgroup(String workgroupId){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String>get("workgroup").get("id"), workgroupId);
			}
		};
	}
	
	public static Specification<Launch> stakeholder(String stakeholderId){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<String>get("stakeholder").get("id"), stakeholderId);
			}
		};
	}
	
	public static Specification<Launch> account(Integer accountId){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer>get("account").get("id"), accountId);
			}
		};
	}
	
	public static Specification<Launch> dateEmission(Date dateEmission){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Date>get("dateEmission"), dateEmission);
			}
		};
	}
	
	public static Specification<Launch> datePay(Date datePay){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Date>get("datePay"), datePay);
			}
		};
	}
	
	public static Specification<Launch> dateLaunch(Date dateLaunch){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Date>get("dateLaunch"), dateLaunch);
			}
		};
	}
	
	public static Specification<Launch> launchBetween(Date date, int delta){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				Calendar start = new GregorianCalendar();
				
				start.setTime(date);
				start.add(Calendar.DATE, delta);
				
				Calendar end = new GregorianCalendar();
				
				end.setTime(date);
				end.add(Calendar.DATE, delta);
				
				return cb.between(root.<Date>get("datePayment"),start.getTime(),end.getTime());
			}
		};
	}
	
	public static Specification<Launch> amountBetween(BigDecimal amount, BigDecimal delta){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.between(root.<BigDecimal>get("amount"),amount.add(delta.multiply(new BigDecimal(-1))),amount.add(delta));
			}
		};
	}
	
	public static Specification<Launch> parcelNumber(Integer parcel){
		return new Specification<Launch>() {
			@Override
			public Predicate toPredicate(Root<Launch> root,	CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.<Integer>get("parcel"),parcel);
			}
		};
	}

}
