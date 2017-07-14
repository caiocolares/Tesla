package br.com.tesla.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.com.tesla.auth.model.entities.User;
import br.com.tesla.core.model.entities.City;
import br.com.tesla.core.model.entities.Person;
import br.com.tesla.core.model.entities.PersonType;
import br.com.tesla.core.model.entities.UF;
import br.com.tesla.core.model.entities.WorkGroup;
import br.com.tesla.core.model.entities.WorkGroupType;
import br.com.tesla.financial.model.dto.DefaultSpecifications;
import br.com.tesla.model.dto.PersonEntitie;
import br.com.tesla.model.dto.PersonSpecification;
import br.com.tesla.repository.BrokerRepository;
import br.com.tesla.repository.CityRepository;
import br.com.tesla.repository.CustomerRepository;
import br.com.tesla.repository.EmployeeRepository;
import br.com.tesla.repository.PersonRepository;
import br.com.tesla.repository.ProviderRepository;
import br.com.tesla.repository.UserRepository;
import br.com.tesla.repository.WorkGroupRepository;
import br.com.tesla.service.ReportService;
import br.com.tesla.util.Pagination;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonRepository repository;

	@Autowired
	private CustomerRepository repositoryCustomer;

	@Autowired
	private EmployeeRepository repositoryEmployee;

	@Autowired
	private ProviderRepository repositoryProvider;

	@Autowired
	private WorkGroupRepository repositoryWorkGroup;

	@Autowired
	private BrokerRepository repositoryBroker;

	@Autowired
	private UserRepository repositoryUser;

	@Autowired
	private CityRepository repositoryCity;

	@Autowired
	private ReportService reportService;

	private UF defaultUF = UF.CE;

	@Value("${tesla.person.user.defaultPassword}")
	private String defaultPassword;

	@RequestMapping(value = "/report/{id}", method = RequestMethod.GET)
	@ResponseBody
	@PreAuthorize("hasRole('PERSON_REPORT')")
	public void report(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws JRException, IOException {
		Map<String, Object> params = new HashMap<>();

		Person person = repository.findOne(id);
		ArrayList<Person> list = new ArrayList<>();
		list.add(person);

		JasperPrint jasperPrint = reportService.loadReport("rptPersonDetail", params, list, request);

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=report-person-detail.pdf");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

	@RequestMapping
	@PreAuthorize("hasRole('PERSON')")
	public ModelAndView findByFilter(@RequestParam(value = "name", defaultValue = "", required = false) String name,
			@RequestParam(value = "fantasy", defaultValue = "", required = false) String fantasy,
			@RequestParam(value = "personType", defaultValue = "FISICA", required = false) PersonType personType,
			@RequestParam(value = "entitie", required = false) String[] entitie,
			@RequestParam(value = "cpf", defaultValue = "", required = false) String cpf,
			@RequestParam(value = "rg", defaultValue = "", required = false) String rg,
			@RequestParam(value = "cnpj", defaultValue = "", required = false) String cnpj,
			@RequestParam(value = "ie", defaultValue = "", required = false) String ie,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
			@RequestParam(value = "success", required = false) Boolean success) {

		Map<String, PersonEntitie> entities = new HashMap<>();
		entities.put("Customer",
				PersonEntitie.builder().name("Cliente").specification(PersonSpecification.isCustomer()).build());
		entities.put("Provider",
				PersonEntitie.builder().name("Fornecedor").specification(PersonSpecification.isProvider()).build());
		entities.put("Employee",
				PersonEntitie.builder().name("Funcionário").specification(PersonSpecification.isEmployee()).build());
		entities.put("Broker",
				PersonEntitie.builder().name("Representante").specification(PersonSpecification.isBroker()).build());
		entities.put("Workgroup",
				PersonEntitie.builder().name("Empresa").specification(PersonSpecification.isWorkgroup()).build());
		entities.put("User",
				PersonEntitie.builder().name("Usuário").specification(PersonSpecification.isUser()).build());

		ModelAndView view = new ModelAndView("person/list");
		List<Specification<Person>> list = new ArrayList<>();
		list.add(PersonSpecification.personType(personType));
		if (name != null && !name.isEmpty()) {
			list.add(PersonSpecification.name(name));
		}
		if (fantasy != null && !fantasy.isEmpty()) {
			list.add(PersonSpecification.fantasy(fantasy));
		}
		if (cpf != null && !cpf.isEmpty()) {
			list.add(PersonSpecification.cpf(cpf));
		}
		if (rg != null && !rg.isEmpty()) {
			list.add(PersonSpecification.rg(rg));
		}
		if (cnpj != null && !cnpj.isEmpty()) {
			list.add(PersonSpecification.cnpj(cnpj));
		}
		if (ie != null && !ie.isEmpty()) {
			list.add(PersonSpecification.ie(ie));
		}
		if (entitie != null) {
			for (int i = 0; i < entitie.length; i++) {
				PersonEntitie pe = entities.get(entitie[i]);
				if (pe != null) {
					pe.setEnabled(true);
					list.add(pe.getSpecification());
				}
			}
		}

		Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.ASC, "name"));
		Page<Person> page = repository.findAll(DefaultSpecifications.<Person> mount(list), pageable);
		Map<String, Integer> pagination = Pagination.pagination(page);
		view.addAllObjects(pagination);
		view.addObject("list", page.getContent());
		view.addObject("name", name);
		view.addObject("fantasy", fantasy);
		view.addObject("personType", personType);
		view.addObject("entitie", entitie);
		view.addObject("entities", entities);
		view.addObject("cpf", cpf);
		view.addObject("rg", rg);
		view.addObject("cnpj", cnpj);
		view.addObject("ie", ie);
		view.addObject("pageNumber", pageNumber);
		view.addObject("success", success);
		return view;
	}

	@RequestMapping("/form")
	@PreAuthorize("hasRole('PERSON_SAVE')")
	public ModelAndView insert() {
		ModelAndView view = new ModelAndView("person/form");
		populateForm(view);
		return view;
	}

	private void populateForm(ModelAndView view) {
		List<City> listCity = repositoryCity.findByUf(defaultUF);
		List<WorkGroup> listWorkGroup = repositoryWorkGroup.findByWorkgroupType(WorkGroupType.HEAD_OFFICE);
		view.addObject("defaultUF", defaultUF);
		view.addObject("listUF", UF.getList());
		view.addObject("listCity", listCity);
		view.addObject("listWorkGroup", listWorkGroup);
	}

	@RequestMapping("/form/{id}")
	@PreAuthorize("hasRole('PERSON_DETAIL')")
	public ModelAndView findById(@PathVariable("id") String id) {
		ModelAndView view = new ModelAndView("person/form");
		Person person = repository.findOne(id);
		if (person.getCity() != null)
			defaultUF = person.getCity().getUf();

		view.addObject("person", person);
		populateForm(view);
		return view;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@Transactional
	@PreAuthorize("hasRole('PERSON_SAVE')")
	public ModelAndView save(@ModelAttribute("person") @Validated Person person, BindingResult result,
			HttpServletRequest request) {

		ModelAndView view = new ModelAndView();

		// Check Errors
		if (person.getAddress() == null || person.getPhones() == null) {
			ObjectError error = new ObjectError("error", "É necessário ter um endereço e um contato!");
			result.addError(error);
		}
		if (person.getWorkgroup().getEnabled() && person.getWorkgroup().getWorkgroupType() == WorkGroupType.SUBSIDIARY
				&& person.getWorkgroup().getMaster() == null) {
			ObjectError error = new ObjectError("error",
					"É necessário informar a empresa matriz, para uma empresa filial!");
			result.addError(error);
		}
		if (person.getWorkgroup().getEnabled() && person.getWorkgroup().getWorkgroupType() == WorkGroupType.SUBSIDIARY
				&& person.getWorkgroup().getMaster().getId().equals(person.getId())) {
			ObjectError error = new ObjectError("error",
					"Esta empresa já está cadastrada como matriz. Informe outra empresa!");
			result.addError(error);
		}
		if (result.hasErrors()) {
			view.setViewName("person/form");
			view.addObject("person", person);
			view.addObject("errors", result.getAllErrors());
			populateForm(view);
			return view;
		}

		// Set UUIDs and User Password
		if (person.getId() == null || person.getId().isEmpty()) {
			person.setId(UUID.randomUUID().toString());
		} else {
			// Verify edit user, retrive state user properties
			User user = repositoryUser.findOne(person.getId());
			user.setLogin(person.getUser().getLogin());
			user.setActive(person.getUser().getActive());
			person.setUser(user);
		}
		person.getAddress().stream().forEach(a -> {
			if (a.getId() == null || a.getId().isEmpty())
				a.setId(UUID.randomUUID().toString());
		});
		person.getPhones().stream().forEach(p -> {
			if (p.getId() == null || p.getId().isEmpty())
				p.setId(UUID.randomUUID().toString());
		});
		if (person.getUser().getLogin() == null || person.getUser().getLogin().isEmpty()) {
			person.getUser().setLogin(person.getId());
		}
		if (person.getUser().getPassword() == null) {
			Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder();
			String newPasswordMD5 = encoderMD5.encodePassword(defaultPassword, null);
			person.getUser().setPassword(newPasswordMD5);
		}

		try {
			repository.save(person);
			repositoryCustomer.save(person.getCustomer());
			repositoryProvider.save(person.getProvider());
			repositoryWorkGroup.save(person.getWorkgroup());
			repositoryEmployee.save(person.getEmployee());
			repositoryBroker.save(person.getBroker());
			repositoryUser.save(person.getUser());
			view.setViewName("redirect:/person");
			view.addObject("success", true);
		} catch (Exception ex) {
			System.out.println(ex);
			view.setViewName("person/form");
			view.addObject("erro", ex.getMessage());
			return view;
		}

		return view;
	}

	@Transactional(readOnly = true)
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public @ResponseBody Map<String, Object> findByUf(
			@RequestParam(value = "name", defaultValue = "", required = false) String name,
			@RequestParam(value = "fantasy", defaultValue = "", required = false) String fantasy,
			@RequestParam(value = "personType", defaultValue = "FISICA", required = false) PersonType personType,
			@RequestParam(value = "cpf", defaultValue = "", required = false) String cpf,
			@RequestParam(value = "rg", defaultValue = "", required = false) String rg,
			@RequestParam(value = "cnpj", defaultValue = "", required = false) String cnpj,
			@RequestParam(value = "ie", defaultValue = "", required = false) String ie,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber) {
		List<Specification<Person>> list = new ArrayList<>();
		list.add(PersonSpecification.personType(personType));
		if (name != null && !name.isEmpty()) {
			list.add(PersonSpecification.name(name));
		}
		if (fantasy != null && !fantasy.isEmpty()) {
			list.add(PersonSpecification.fantasy(fantasy));
		}
		if (cpf != null && !cpf.isEmpty()) {
			list.add(PersonSpecification.cpf(cpf));
		}
		if (rg != null && !rg.isEmpty()) {
			list.add(PersonSpecification.rg(rg));
		}
		if (cnpj != null && !cnpj.isEmpty()) {
			list.add(PersonSpecification.cnpj(cnpj));
		}
		if (ie != null && !ie.isEmpty()) {
			list.add(PersonSpecification.ie(ie));
		}
		Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.ASC, "name"));
		Page<Person> page = repository.findAll(DefaultSpecifications.<Person> mount(list), pageable);
		Map<String, Integer> pagination = Pagination.pagination(page);

		Map<String, Object> ret = new HashMap<>();
		ret.put("pagination", pagination);
		ret.put("list", page.getContent());
		ret.put("name", name);
		ret.put("fantasy", fantasy);
		ret.put("personType", personType);
		ret.put("cpf", cpf);
		ret.put("rg", rg);
		ret.put("cnpj", cnpj);
		ret.put("ie", ie);
		ret.put("pageNumber", pageNumber);
		return ret;
	}

}
