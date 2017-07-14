package br.com.legacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import br.com.legacy.model.entities.Shop;

//@RepositoryRestResource(path="shop")
public interface ShopRepository extends JpaRepository<Shop, Integer> , JpaSpecificationExecutor<Shop>{
	

}
