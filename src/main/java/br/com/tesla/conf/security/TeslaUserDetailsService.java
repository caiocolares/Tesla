package br.com.tesla.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.tesla.auth.model.entities.User;
import br.com.tesla.repository.UserRepository;

@Component("userDetailsService")
@Order(Ordered.HIGHEST_PRECEDENCE+10)
public class TeslaUserDetailsService implements UserDetailsService {
	
	@Autowired
    UserRepository repository;
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public User loadUserByUsername(String login) throws UsernameNotFoundException {
		
		User user = repository.findByLogin(login);
		try{
			user.getAuthorities();
		}
		catch(Exception e){
			//
		}		
		return user;
	}

}
