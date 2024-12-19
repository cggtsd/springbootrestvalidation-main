package cgg.springboot.rest.validation.springbootrestvalidation.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import cgg.springboot.rest.validation.springbootrestvalidation.dao.UserRepository;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;
import cgg.springboot.rest.validation.springbootrestvalidation.exceptions.ResourceNotFoundException;

@Service
public class CustomUserDetailService  implements UserDetailsService{

	@Autowired
	private UserRepository userRepo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// loading user from database by username
		
		User1 user=this.userRepo.findByEmail(username).orElseThrow(()->new ResourceNotFoundException("User ", "email "+username, 0));
		return user;
	}

}
