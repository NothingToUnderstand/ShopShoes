package com.viktor.task.shoes.manager.web.configuration;


import com.viktor.task.shoes.manager.web.service.UserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	private UserDetailService userDetailService;
	

	@Autowired
	public void setUserDetailService(UserDetailService userDetailService) {
		this.userDetailService = userDetailService;
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.cors().and().csrf().disable();
		
		// csrfTokenRepository(customCsrfTokenRepository())
		// .and().addFilterAfter(new CustomCsrfHeaderFilter(),CsrfFilter.class)
		// .rememberMe().key("DEVELNOTES_REMEMBER_TOKEN")
		
		// .authorizeRequests()
		// .antMatchers("/shoes/**","/users/**","/rest/**").permitAll()
		// .antMatchers("/login").permitAll()
		// .and().formLogin()
		// .and().logout()
		// .and().httpBasic();
		

		// .and().exceptionHandling().authenticationEntryPoint(new Http403ForbiddenEntryPoint());

	
	}
	


	@Override
 public void configure(AuthenticationManagerBuilder builder) 
throws Exception {
   builder.userDetailsService(userDetailService)
   .passwordEncoder(passwordEncoder());
   
   
 }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(userDetailService);
		return daoAuthenticationProvider;
	}

	@Bean
public CsrfTokenRepository customCsrfTokenRepository() {
 
  HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
  repository.setHeaderName("X-XSRF-TOKEN");
 
  return repository;
}

@Bean
public TokenBasedRememberMeServices rememberMeServices() {
 
  TokenBasedRememberMeServices service =
  new TokenBasedRememberMeServices(
  "DEVELNOTES_REMEMBER_TOKEN", userDetailService);
 
  service.setCookieName("DEVELNOTES_REMEMBER_ME_COOKIE");
  service.setUseSecureCookie(false);
  service.setAlwaysRemember(false);
 
  return service;
}
@Bean("authenticationManager")
@Override
public AuthenticationManager authenticationManagerBean() throws Exception {
	return super.authenticationManagerBean();
}

}