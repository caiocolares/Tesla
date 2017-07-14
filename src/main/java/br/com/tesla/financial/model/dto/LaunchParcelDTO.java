package br.com.tesla.financial.model.dto;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class LaunchParcelDTO {
	
	private Integer parcel;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date datePay;
	
	private BigDecimal amount;
	
}
