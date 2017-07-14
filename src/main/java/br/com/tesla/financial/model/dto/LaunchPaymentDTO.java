package br.com.tesla.financial.model.dto;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.tesla.core.model.entities.Person;
import br.com.tesla.financial.model.entities.Account;
import br.com.tesla.financial.model.entities.LaunchDocument;
import br.com.tesla.financial.model.entities.LaunchDocumentOrigin;
import lombok.Data;

@Data
public class LaunchPaymentDTO {

	private Person workgroup;
	private Person stakeholder;
	private Account account;
	private LaunchDocument document;
	private String documentNumber;
	private LaunchDocumentOrigin documentOrigin;
	private String documentOriginNumber;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date dateEmisson;
	
	private List<LaunchParcelDTO> parcels;
	
}