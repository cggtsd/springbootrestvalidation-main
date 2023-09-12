package cgg.springboot.rest.validation.springbootrestvalidation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import cgg.springboot.rest.validation.springbootrestvalidation.security.CustomUserDetailService;

@Configuration
public class MySecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/v1/auth/register")
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
		
		
		return http.build();
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
	    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();	
	    provider.setUserDetailsService(customUserDetailService);
	    provider.setPasswordEncoder(passwordEncoder());
	    return provider;
	}
	
//	@Bean
//	public UserDetailsService userDetailsService() {
//		
//		return new CustomUserDetailService();
//	}
	
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration config) throws Exception{
		return config.getAuthenticationManager();
	}
	
}
