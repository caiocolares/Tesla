package br.com.tesla;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages={"br.com.tesla.conf",
										 "br.com.tesla.service",
										 "br.com.tesla.bank.service",
										 "br.com.tesla.controller",
										 "br.com.tesla.survey.controller",
										 "br.com.tesla.financial.controller",
										 "br.com.legacy.controller"})
public class TeslaApplication extends SpringBootServletInitializer{
	
	public static void main(String[] args) {		
		SpringApplication.run(TeslaApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(applicationClass);
	}
	
	private static Class<TeslaApplication> applicationClass = TeslaApplication.class;
}
