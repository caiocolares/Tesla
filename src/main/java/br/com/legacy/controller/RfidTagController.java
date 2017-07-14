package br.com.legacy.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import br.com.legacy.model.entities.RfidTag;
import br.com.legacy.model.entities.Shop;
import br.com.legacy.repository.RfidTagRepository;
import br.com.legacy.repository.ShopRepository;

@RestController
@RequestMapping("/rfid")
@Scope(value=WebApplicationContext.SCOPE_REQUEST)
public class RfidTagController {

	
	@Autowired
	private RfidTagRepository repository;
	@Autowired
	private ShopRepository shopRepository;
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/verify")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView verityIpAddress(Principal user){
		
		List<Shop> list = shopRepository.findAll();
		ModelAndView model = new ModelAndView("verify");
		
		if(!list.isEmpty()){
			model.addObject("shop", list.get(0));
		}
		return model;
	}
	
	@Transactional(readOnly=true)
	@RequestMapping(value="/tag/{id}",method=RequestMethod.GET,produces="application/json;charset=UTF-8")
	public @ResponseBody RfidTag getTagById(@PathVariable("id") String id){
		return repository.findOne(id);
	}
	
}
