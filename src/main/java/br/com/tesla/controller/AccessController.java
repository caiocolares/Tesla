package br.com.tesla.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.com.tesla.auth.model.entities.Group;
import br.com.tesla.auth.model.entities.Module;
import br.com.tesla.auth.model.entities.ModuleResource;
import br.com.tesla.auth.model.entities.Transaction;
import br.com.tesla.auth.model.entities.User;
import br.com.tesla.auth.repository.GroupRepository;
import br.com.tesla.auth.repository.ModuleRepository;
import br.com.tesla.auth.repository.ModuleResourceRepository;
import br.com.tesla.auth.repository.TransactionRepository;
import br.com.tesla.repository.UserRepository;
import br.com.tesla.util.Pagination;

@Controller
@RequestMapping("/access")
public class AccessController {

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ModuleRepository moduleRepository;

	@Autowired
	private ModuleResourceRepository resourceRepository;

	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private UserRepository userRepository;

	@RequestMapping("/user")
	public ModelAndView listUser(@RequestParam(value = "name", defaultValue = "", required = false) String name,
			@RequestParam(value = "login", defaultValue = "", required = false) String login,
			@RequestParam(value = "active", defaultValue = "true", required = true) Boolean active,
			@RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber) {

		ModelAndView view = new ModelAndView("access/user");

		if (!name.isEmpty() || !login.isEmpty()) {
			Pageable pageable = new PageRequest(pageNumber, 10, new Sort(Sort.Direction.ASC, "person.name"));
			Page<User> page = userRepository.findAllByActiveAndPersonNameOrLogin(active, "%"+name+"%", "%"+login+"%", pageable);
			Map<String, Integer> pagination = Pagination.pagination(page);
			view.addAllObjects(pagination);
			view.addObject("list", page.getContent());
		}

		view.addObject("name", name);
		view.addObject("login", login);
		view.addObject("active", active);
		view.addObject("pageNumber", pageNumber);
		return view;
	}
	
	@RequestMapping("/user/edit")
	public ModelAndView editUser(User user, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("access/userGroupTransaction");
		String moduleId = request.getParameter("moduleId");
		String resourceId = request.getParameter("resourceId");
		String error = request.getParameter("error");
		String success = request.getParameter("success");
		
		List<Group> groups = (List<Group>) groupRepository.findAll();
		List<Group> groupsSelected = user.getGroups();
		groups.removeAll(groupsSelected);
		
		List<Module> modules = (List<Module>) moduleRepository.findAll();
		List<ModuleResource> resources = null;
		List<Transaction> transactions = null;
		List<Transaction> transactionsSelected = null;
		
		if (moduleId != null && !moduleId.isEmpty()) {
			Module moduleSelected = modules.stream().filter(m -> m.getId() == Integer.parseInt(moduleId)).findFirst().get();
			resources = resourceRepository.findByModule(moduleSelected);
			if (resourceId != null && !resourceId.isEmpty()) {
				ModuleResource resourceSelected = resources.stream().filter(r -> r.getId() == Integer.parseInt(resourceId)).findFirst().get();
				transactions = transactionRepository.findByModuleResource(resourceSelected);
				transactionsSelected = user.getUserTransactions();
				transactions.removeAll(transactionsSelected);
			}
		}
		
		view.addObject("groups", groups);
		view.addObject("groupsSelected", groupsSelected);
		view.addObject("moduleId", moduleId);
		view.addObject("resourceId", resourceId);
		view.addObject("modules", modules);
		view.addObject("resources", resources);
		view.addObject("transactions", transactions);
		view.addObject("transactionsSelected", transactionsSelected);
		view.addObject("error", error);
		view.addObject("success", success);
		return view;
	}
	
	@RequestMapping(value = "/user/save", method = RequestMethod.POST)
	@Transactional
	public ModelAndView saveUser(User user, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("redirect:/access/user/edit");
		view.addObject("user", request.getParameter("user"));
		view.addObject("moduleId", request.getParameter("moduleId"));
		view.addObject("resourceId", request.getParameter("resourceId"));
		try {
			userRepository.save(user);
			view.addObject("success", true);
		} catch (Exception ex) {
			System.out.println(ex);
			view.addObject("error", ex.getMessage());
		}
		return view;
		
	}
	
	@RequestMapping("/group")
	public ModelAndView listGroup(@RequestParam(value = "groupId", required = false) Integer groupId,
			@RequestParam(value = "moduleId", required = false) Integer moduleId,
			@RequestParam(value = "resourceId", required = false) Integer resourceId,
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "success", required = false) Boolean success) {
		ModelAndView view = new ModelAndView("access/group");
		List<Group> groups = (List<Group>) groupRepository.findAll();
		List<Module> modules = null;
		List<ModuleResource> resources = null;
		List<Transaction> transactions = null;
		List<Transaction> transactionsSelected = null;
		Group groupSelected = null;
		if (groupId != null) {
			modules = (List<Module>) moduleRepository.findAll();
			if (moduleId != null) {
				Module moduleSelected = modules.stream().filter(m -> m.getId() == moduleId).findFirst().get();
				resources = resourceRepository.findByModule(moduleSelected);
				if (resourceId != null) {

					groupSelected = groups.stream().filter(g -> g.getId() == groupId).findFirst().get();
					transactionsSelected = groupSelected.getTransactions();

					ModuleResource resourceSelected = resources.stream().filter(r -> r.getId() == resourceId)
							.findFirst().get();
					transactions = transactionRepository.findByModuleResource(resourceSelected);
					transactions.removeAll(transactionsSelected);

				}
			}
		}
		view.addObject("groupId", groupId);
		view.addObject("groupSelected", groupSelected);
		view.addObject("groups", groups);
		view.addObject("moduleId", moduleId);
		view.addObject("modules", modules);
		view.addObject("resourceId", resourceId);
		view.addObject("resources", resources);
		view.addObject("transactions", transactions);
		view.addObject("transactionsSelected", transactionsSelected);
		view.addObject("error", error);
		view.addObject("success", success);
		return view;
	}

	@RequestMapping(value = "/group/save", method = RequestMethod.POST)
	@Transactional
	public ModelAndView saveGroup(@ModelAttribute("group") @Validated Group group, BindingResult result, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("redirect:/access/group");
		if (result.hasErrors()) {
			view.addObject("error", result.getAllErrors().get(0).getDefaultMessage());
		} else {
			try {
				groupRepository.save(group);
				view.addObject("success", true);
			} catch (Exception ex) {
				System.out.println(ex);
				view.addObject("error", ex.getMessage());
			}
		}
		view.addObject("groupId", request.getParameter("groupId"));
		view.addObject("moduleId", request.getParameter("moduleId"));
		view.addObject("resourceId", request.getParameter("resourceId"));
		return view;
	}

	@RequestMapping("/editPassword")
	public ModelAndView editPassword(@RequestParam(value = "password_old", defaultValue = "", required = true) String passwordOld,
			@RequestParam(value = "password_new", defaultValue = "", required = true) String passwordNew,
			@RequestParam(value = "password_new_repeat", defaultValue = "", required = true) String passwordNewRepeat,
			HttpServletRequest request) {
		ModelAndView view = new ModelAndView("access/editPassword");
		
		if("POST".equalsIgnoreCase(request.getMethod())){
			if(passwordOld.isEmpty() || passwordNew.isEmpty() || passwordNewRepeat.isEmpty()){
				view.addObject("error", "Preencha os campos corretamente!");
				return view;
			}				
			if(!passwordNew.equals(passwordNewRepeat)){
				view.addObject("error", "Nova senha não foi repetida corretamente. Preencha os campos corretamente!");
				return view;
			}
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user =  (User) auth.getPrincipal();
			Md5PasswordEncoder encoderMD5 = new Md5PasswordEncoder();
			String passwordOldMD5 = encoderMD5.encodePassword(passwordOld, null);
			String passwordNewMD5 = encoderMD5.encodePassword(passwordNew, null);
			if(!user.getPassword().equals(passwordOldMD5)){
				view.addObject("error", "Senha antiga incorreta. Preencha a senha antiga novamente!");
				return view;			
			}		
			try {
				user.setPassword(passwordNewMD5);
				user.setPasswordUpdated(true);
				userRepository.save(user);
				view.addObject("success", true);
			} catch (Exception ex) {
				System.out.println(ex);
				view.addObject("error", ex.getMessage());
			}
		}
		return view;
	}

}