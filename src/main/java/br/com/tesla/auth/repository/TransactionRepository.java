package br.com.tesla.auth.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.tesla.auth.model.entities.ModuleResource;
import br.com.tesla.auth.model.entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction, Integer> {

	List<Transaction> findByModuleResource(ModuleResource moduleResource);
	
}
