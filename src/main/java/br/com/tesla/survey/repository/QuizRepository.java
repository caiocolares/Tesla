package br.com.tesla.survey.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.tesla.survey.model.entities.Quiz;

public interface QuizRepository extends CrudRepository<Quiz, String> {

}
