package cgg.springboot.rest.validation.springbootrestvalidation.payload;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.UserDto;
import lombok.Data;

@Data
public class JwtAuthResponse {

	private String token;
	
	private UserDto user;
}
