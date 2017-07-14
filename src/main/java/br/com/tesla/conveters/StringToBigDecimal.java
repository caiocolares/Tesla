package br.com.tesla.conveters;

import java.math.BigDecimal;

import org.springframework.core.convert.converter.Converter;

public class StringToBigDecimal implements Converter<String, BigDecimal> {

	@Override
	public BigDecimal convert(String source) {
		
		//3.500,78
		//3500,78
		//3500.78
		source = source.replace(".", "").replace(",", ".");
		return new BigDecimal(source);
	}	
	
}