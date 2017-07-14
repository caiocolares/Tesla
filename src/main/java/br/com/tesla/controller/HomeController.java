package br.com.tesla.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.tesla.core.model.entities.Person;
import br.com.tesla.repository.PersonRepository;

@RestController
public class HomeController {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private PersonRepository repository;

	@RequestMapping(path = "/login")
	public ModelAndView login() {
		return new ModelAndView("login");
	}
	
	@Transactional
	@RequestMapping(path = "/password", method=RequestMethod.POST)
	public ModelAndView forgotPassword(String email) {
		ModelAndView view = new ModelAndView("login");
		Person person = repository.findByEmail(email);

		if(person == null){
			view.addObject("error", "Usuário não localizado!");
		}else{
			sendmail(email, "Tesla - Requisição de Nova Senha ", "Sua nova senha é: "+person.getUser().generateNewPassword());
			view.addObject("info", "Nova senha enviada para email do usuário!");
		}
		
		return view;
	}
	
	

	@RequestMapping(path = "/home")
	public ModelAndView home() {
		return new ModelAndView("home");
	}

	@RequestMapping(path = "/mail/{to}/{subject}")
	public String sendmail(@PathVariable("to") String to,
			@PathVariable("subject") String subject) {
		sendmail(to, subject,"Email de teste");

		return "Enviado";
	}

	private void sendmail(String to, String subject,String message) {
		SimpleMailMessage mail = new SimpleMailMessage();

		mail.setTo(to);
		mail.setSubject(subject);
		mail.setText(message);

		mailSender.send(mail);
	}

	@RequestMapping(path = {"/", ""})
	public ModelAndView init() {
		return new ModelAndView("redirect:/home");
	}

}
