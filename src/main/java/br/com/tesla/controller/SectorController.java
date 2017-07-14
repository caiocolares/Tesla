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

import br.com.tesla.core.model.entities.Sector;
import br.com.tesla.core.model.entities.WorkGroup;
import br.com.tesla.financial.model.dto.DefaultSpecifications;
import br.com.tesla.model.dto.SectorSpecification;
import br.com.tesla.repository.SectorRepository;
import br.com.tesla.repository.WorkGroupRepository;
import br.com.tesla.util.Pagination;

@Controller
@RequestMapping(value = "/core/sector")
public class SectorController {

	@Autowired
	private SectorRepository sectorRepository;

	@Autowired
	private WorkGroupRepository workGroupRepository;

	@RequestMapping
	public ModelAndView list(@RequestParam(value = "workgroupId", required = false) String workgroupId,
			@RequestParam(value = "sectorId", required = false) Integer sectorId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "success", required = false) Boolean success) {

		ModelAndView view = new ModelAndView("core/sector/list");
		Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.ASC, "name"));
		List<Specification<Sector>> list = new ArrayList<>();
		if(name != null && !name.isEmpty()){
			list.add(SectorSpecification.name(name));
		}
		if(workgroupId != null && !workgroupId.isEmpty()){
			list.add(SectorSpecification.workgroup(workgroupId));			
		}
		if(sectorId != null){
			list.add(SectorSpecification.headOffice(sectorId));
		}
		Page<Sector> page = sectorRepository.findAll(DefaultSpecifications.<Sector>mount(list), pageable);
		List<WorkGroup> workgroups = workGroupRepository.findAll(new Sort(Sort.Direction.ASC, "person.name"));
		List<Sector> sectors = sectorRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		Map<String, Integer> pagination = Pagination.pagination(page);
		view.addAllObjects(pagination);
		view.addObject("list", page.getContent());
		view.addObject("workgroups", workgroups);
		view.addObject("sectors", sectors);
		view.addObject("workgroupId", workgroupId);
		view.addObject("sectorId", sectorId);
		view.addObject("name", name);
		view.addObject("pageNumber", pageNumber);
		view.addObject("success", success);
		return view;
	}

	@RequestMapping(value = "/form")
	public ModelAndView form(Sector sector) {
		ModelAndView view = new ModelAndView("core/sector/form");
		List<WorkGroup> workgroups = workGroupRepository.findAll(new Sort(Sort.Direction.ASC, "person.name"));
		List<Sector> sectors = sectorRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		view.addObject("sector", sectors);
		view.addObject("workgroups", workgroups);
		view.addObject("sectors", sectors);
		if (sector.getId() != null) {
			view.addObject("sector", sector);
			view.addObject("edit", true);
		}
		return view;
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public ModelAndView save(Sector sector) {
		ModelAndView view = new ModelAndView();
		try {
			sectorRepository.save(sector);
			view.setViewName("redirect:/core/sector");
			view.addObject("success", true);
		} catch (Exception ex) {
			view.setViewName("core/sector/form");
			view.addObject("error", ex.getMessage());
			view.addObject("sector", sector);
		}
		return view;
	}

}