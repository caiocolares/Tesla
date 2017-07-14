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
import br.com.tesla.core.model.entities.Role;
import br.com.tesla.financial.model.dto.DefaultSpecifications;
import br.com.tesla.model.dto.RoleSpecification;
import br.com.tesla.repository.RoleRepository;
import br.com.tesla.util.Pagination;

@Controller
@RequestMapping("/core/role")
public class RoleController {

	@Autowired
	private RoleRepository roleRepository;
	
	@RequestMapping
	public ModelAndView list(@RequestParam(value = "roleId", required = false) Integer roleId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "success", required = false) Boolean success) {

		ModelAndView view = new ModelAndView("core/role/list");
		Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.ASC, "name"));
		List<Specification<Role>> list = new ArrayList<>();
		if(name != null && !name.isEmpty()){
			list.add(RoleSpecification.name(name));
		}
		if(roleId != null){
			list.add(RoleSpecification.master(roleId));
		}
		Page<Role> page = roleRepository.findAll(DefaultSpecifications.<Role>mount(list), pageable);
		List<Role> roles = roleRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		Map<String, Integer> pagination = Pagination.pagination(page);
		view.addAllObjects(pagination);
		view.addObject("list", page.getContent());
		view.addObject("roles", roles);
		view.addObject("roleId", roleId);
		view.addObject("name", name);
		view.addObject("pageNumber", pageNumber);
		view.addObject("success", success);
		return view;
	}
	
	@RequestMapping(value = "/form")
	public ModelAndView form(Role role) {
		ModelAndView view = new ModelAndView("core/role/form");
		List<Role> roles = roleRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		view.addObject("roles", roles);
		view.addObject("educations", Education.values());
		if (role.getId() != null) {
			view.addObject("role", role);
			view.addObject("edit", true);
		}
		return view;
	}
	
	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView save(Role role) {
		ModelAndView view = new ModelAndView();
		try {
			roleRepository.save(role);
			view.setViewName("redirect:/core/role");
			view.addObject("success", true);
		} catch (Exception ex) {
			view.setViewName("core/role/form");
			view.addObject("error", ex.getMessage());
			view.addObject("role", role);
		}
		return view;
	}	
	
}
