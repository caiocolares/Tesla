package br.com.tesla.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.tesla.core.model.entities.City;
import br.com.tesla.core.model.entities.UF;
import br.com.tesla.repository.CityRepository;


@Controller
@RequestMapping("/city")
public class CityController {
	
	@Autowired
	private CityRepository repository;
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/{uf}",method=RequestMethod.GET)
	public @ResponseBody List<City> findByUf(@PathVariable("uf") UF uf){
		return repository.findByUf(uf);
	}
	
}
