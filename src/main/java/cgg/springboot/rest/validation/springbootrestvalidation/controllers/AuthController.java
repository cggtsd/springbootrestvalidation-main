package cgg.springboot.rest.validation.springbootrestvalidation.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.UserDto;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;
import cgg.springboot.rest.validation.springbootrestvalidation.payload.JwtAuthRequest;
import cgg.springboot.rest.validation.springbootrestvalidation.payload.JwtAuthResponse;
import cgg.springboot.rest.validation.springbootrestvalidation.security.CustomUserDetailService;
import cgg.springboot.rest.validation.springbootrestvalidation.security.JwtTokenHelper;
import cgg.springboot.rest.validation.springbootrestvalidation.services.UserService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomUserDetailService customUserDetailService;
	
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	
	@Autowired
	private ModelMapper mapper;
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtRequest) throws Exception{
		
		System.out.println(jwtRequest);
		
		try {
			this.authenticationManager
			.authenticate(
			new UsernamePasswordAuthenticationToken(
			jwtRequest.getUsername(),jwtRequest.getPassword()));
		}
		catch(UsernameNotFoundException e) {
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		catch(BadCredentialsException e)
		{
			e.printStackTrace();
			throw new Exception("Bad Credentials");
		}
		UserDetails userDetails = this.customUserDetailService.loadUserByUsername(jwtRequest.getUsername());
	    System.out.println(userDetails);
		String token = this.jwtTokenHelper.generateToken(userDetails);
	    System.out.println("JWT"+token);
	    JwtAuthResponse response = new JwtAuthResponse();
	    //{"token":"value"}
	    response.setToken(token);
	    response.setUser(this.mapper.map((User1)userDetails, UserDto.class));
	    
	    return ResponseEntity.ok(response);
	}
	
	//register new user api
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto usetDto){
		
		UserDto registeredUser = this.userService.registerNewUser(usetDto);
		
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}
	
}
