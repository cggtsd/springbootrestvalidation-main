package cgg.springboot.rest.validation.springbootrestvalidation.payload;

import lombok.Data;

@Data
public class JwtAuthRequest {

	private String username;
	private String password;
}
