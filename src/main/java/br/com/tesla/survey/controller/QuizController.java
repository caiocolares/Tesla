package br.com.tesla.survey.controller;


import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.tesla.survey.model.entities.Question;
import br.com.tesla.survey.model.entities.Quiz;
import br.com.tesla.survey.repository.QuizRepository;

@Controller
@RequestMapping("/survey")
public class QuizController {
	
	@Autowired
	private QuizRepository quizRepository;

	@RequestMapping
	@Transactional(readOnly=true)
	public ModelAndView findAll(){
		Iterable<Quiz> quizList = quizRepository.findAll();
		
		ModelAndView view = new ModelAndView("survey/list");
		view.addObject("quizList", quizList);
		 
		return view;
	}
	

	@RequestMapping("/add")
	public ModelAndView add(){
		ModelAndView view = new ModelAndView("survey/form");
		 
		return view;
	}
	
	@Transactional(readOnly=true)
	@RequestMapping("/{id}")
	public ModelAndView findById(@PathVariable("id") String id){
		ModelAndView view = new ModelAndView("survey/form");
		
		Quiz quiz = quizRepository.findOne(id);
		quiz.getQuestions();
		view.addObject("quiz",quiz);
		
		return view;
	}
	

	@RequestMapping( method=RequestMethod.POST)
	public ModelAndView save(@Validated Quiz quiz,BindingResult result ){
		ModelAndView view = new ModelAndView("survey/list");
		if(result.hasErrors()){
			view.addObject("quiz", quiz);
			view.addObject("erros", result.getAllErrors());
			view.setViewName("survey/form");
			return view;
		}
		if(quiz.getId().isEmpty()){
			quiz.setId(UUID.randomUUID().toString());
		}
		quizRepository.save(quiz);
		return view;
	}
	
	@RequestMapping( value="/question/{quiz}",method=RequestMethod.POST)
	public ModelAndView addQuestion(@PathVariable("quiz") String id, @Validated Question question,BindingResult result ){
		ModelAndView view = new ModelAndView("survey/question");
		if(result.hasErrors()){
			view.addObject("question", question);
			view.addObject("erros", result.getAllErrors());
			view.setViewName("survey/form");
			return view;
		}
		 
		Quiz quiz = quizRepository.findOne(id);
		
		if(question.getId().isEmpty()){
			question.setId(UUID.randomUUID().toString());
		}
		
		quiz.getQuestions().add(question);
		quizRepository.save(quiz);
		view.addObject("quiz", quiz);
		return view;
	}
	
	
}
