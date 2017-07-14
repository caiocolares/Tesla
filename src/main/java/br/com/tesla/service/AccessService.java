package br.com.tesla.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.tesla.auth.model.entities.User;
import br.com.tesla.repository.UserRepository;

@Service("accessService")
public class AccessService {

	private Md5PasswordEncoder encodeMD5 = new Md5PasswordEncoder();

	@Autowired
    UserRepository repository;

	@Transactional(readOnly=true)
	public User findUserByLogin(String login, String password, List<GrantedAuthority> grantedAuths) throws BadCredentialsException {
		if (login.isEmpty()) {
			throw new BadCredentialsException("Login não preenchido!");
		}
		if (password.isEmpty()) {
			throw new BadCredentialsException("Senha obrigatória!");
		}
		
		User user = repository.findByLogin(login);
		if (user != null) {
			if (user.getPassword().equals(encodeMD5.encodePassword(password, null))) {
				grantedAuths.addAll(user.getAuthorities());
	            grantedAuths.add(new SimpleGrantedAuthority("ROLE_DEFAULT"));
				return user;
			} else {
				throw new BadCredentialsException("Senha incorreta");
			}
		}
		throw new BadCredentialsException("Usuário nao encontrado");
	}


}