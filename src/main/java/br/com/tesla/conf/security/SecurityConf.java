package br.com.tesla.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConf extends WebSecurityConfigurerAdapter {

	@Autowired(required = false)
	UserDetailsService userDetailsService;
	
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		csrf().disable().
	    authorizeRequests()
	            .antMatchers("/login","/logout","/password","/resources/**", "/public/**").permitAll()
	            .anyRequest().fullyAuthenticated()
	            .and()
	            .formLogin()
	            .loginPage("/login")
	            .failureUrl("/login?error")
	            .loginProcessingUrl("/login")
	            
	            .and()
	            .httpBasic().and()
	            .logout()
	            .logoutUrl("/logout")
	            .deleteCookies("remember-me")
	            .logoutSuccessUrl("/")
	            
	            .permitAll()
	            .and()
	            .rememberMe();
	}
	

	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**");
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)	throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new Md5PasswordEncoder());
	}
}