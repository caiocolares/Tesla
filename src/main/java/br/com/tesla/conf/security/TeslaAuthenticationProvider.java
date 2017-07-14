package br.com.tesla.conf.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import br.com.tesla.auth.model.entities.User;
import br.com.tesla.service.AccessService;

@Component
public class TeslaAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	AccessService service;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        
        User user = null;
        List<GrantedAuthority> grantedAuths = new ArrayList<>();
		user = service.findUserByLogin(name, password, grantedAuths);
		
        if (user != null) {
        	return new UsernamePasswordAuthenticationToken(user, password, grantedAuths);
        }        
        return null;
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);		
	}

}
