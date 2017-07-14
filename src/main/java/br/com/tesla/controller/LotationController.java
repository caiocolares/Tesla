package br.com.tesla.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.tesla.core.model.entities.Education;
import br.com.tesla.core.model.entities.Lotation;
import br.com.tesla.core.model.entities.Person;
import br.com.tesla.core.model.entities.Role;
import br.com.tesla.core.model.entities.Sector;
import br.com.tesla.financial.model.dto.DefaultSpecifications;
import br.com.tesla.model.dto.LotationSpecification;
import br.com.tesla.repository.LotationRepository;
import br.com.tesla.repository.PersonRepository;
import br.com.tesla.repository.RoleRepository;
import br.com.tesla.repository.SectorRepository;
import br.com.tesla.util.Pagination;

@Controller
@RequestMapping("/core/lotation")
public class LotationController {
	
	@Autowired
	private LotationRepository lotationRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
	@Autowired
	private SectorRepository sectorRepository;
	
	@RequestMapping
	public ModelAndView list(@RequestParam(value = "sectorId", required = false) Integer sectorId,
			@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "personId", required = false) String personId,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "success", required = false) Boolean success) {

		ModelAndView view = new ModelAndView("core/lotation/list");
		Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.ASC, "sector.name"));
		List<Specification<Lotation>> list = new ArrayList<>();
		if(sectorId != null){
			list.add(LotationSpecification.sector(sectorId));
		}
		if(roleId != null){
			list.add(LotationSpecification.role(roleId));
		}
		if (personId != null && !personId.isEmpty()) {
			list.add(LotationSpecification.person(personId));
			Person p = personRepository.findOne(personId);
			view.addObject("person", p);									
		}
		Page<Lotation> page = lotationRepository.findAll(DefaultSpecifications.<Lotation>mount(list), pageable);		
		List<Sector> sectors = sectorRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		List<Role> roles = roleRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		Map<String, Integer> pagination = Pagination.pagination(page);
		view.addAllObjects(pagination);
		view.addObject("list", page.getContent());
		view.addObject("sectors", sectors);
		view.addObject("roles", roles);
		view.addObject("sectorId", sectorId);
		view.addObject("roleId", roleId);
		view.addObject("personId", personId);
		view.addObject("pageNumber", pageNumber);
		view.addObject("success", success);
		return view;
	}
	
	@RequestMapping(value = "/form")
	public ModelAndView form(Lotation lotation) {
		ModelAndView view = new ModelAndView("core/lotation/form");
		List<Sector> sectors = sectorRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		List<Role> roles = roleRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		view.addObject("sectors", sectors);
		view.addObject("roles", roles);
		view.addObject("educations", Education.values());
		if (lotation.getId() != null) {
			view.addObject("lotation", lotation);
			view.addObject("edit", true);
		}
		return view;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView save(Lotation lotation) {
		ModelAndView view = new ModelAndView();
		try {
			lotationRepository.save(lotation);
			view.setViewName("redirect:/core/lotation");
			view.addObject("success", true);
		} catch (Exception ex) {
			view.setViewName("core/lotation/form");
			view.addObject("error", ex.getMessage());
			view.addObject("lotation", lotation);
		}
		return view;
	}

}