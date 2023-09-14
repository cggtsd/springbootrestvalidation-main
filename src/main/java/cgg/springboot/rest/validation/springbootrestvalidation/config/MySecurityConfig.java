package cgg.springboot.rest.validation.springbootrestvalidation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import cgg.springboot.rest.validation.springbootrestvalidation.security.CustomUserDetailService;
import cgg.springboot.rest.validation.springbootrestvalidation.security.JwtAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class MySecurityConfig {

	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtAuthenticationFilter jwtFilter;
	
	@Autowired
	private JwtAuthenticationEntryPoint entryPoint;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers("/api/v1/auth/login")
		.permitAll()
		.requestMatchers("/api/v1/auth/register")
		.permitAll()
		.requestMatchers(HttpMethod.GET)
		.permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.exceptionHandling().authenticationEntryPoint(entryPoint)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		
		
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
	
//	@Bean
//	public FilterRegistrationBean corsFilter() {
//		
//		UrlBasedCorsConfigurationSource source = 
//				new UrlBasedCorsConfigurationSource();
//		
//		
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.addAllowedOriginPattern("*");
//		corsConfiguration.addAllowedHeader("Authorization");
//		corsConfiguration.addAllowedHeader("Content-Type");
//		corsConfiguration.addAllowedHeader("Accept");
//		corsConfiguration.addAllowedHeader("POST");
//		corsConfiguration.addAllowedHeader("GET");
//		corsConfiguration.addAllowedHeader("PUT");
//		corsConfiguration.addAllowedHeader("DELETE");
//		corsConfiguration.addAllowedHeader("OPTIONS");
//		corsConfiguration.setMaxAge(3600L);
//		
//		
//		source.registerCorsConfiguration("/**", corsConfiguration);
//		
//		FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter((CorsConfigurationSource) source));
//		
//		return bean;
//	}
	
}
