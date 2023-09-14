package cgg.springboot.rest.validation.springbootrestvalidation;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import cgg.springboot.rest.validation.springbootrestvalidation.config.AppConstants;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.RoleRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Role;

@SpringBootApplication
public class SpringbootrestvalidationApplication  implements CommandLineRunner{

	@Autowired
	private RoleRepo roleRepo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringbootrestvalidationApplication.class, args);
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try {
			Role role = new Role();
			role.setId(AppConstants.ROLE_ADMIN);
			role.setName("ROLE_ADMIN");
			
			Role role1 = new Role();
			role1.setId(AppConstants.ROLE_NORMAL);
			role1.setName("ROLE_NORMAL");
			
			List<Role> roles = List.of(role,role1);
			
			List<Role> result = this.roleRepo.saveAll(roles);
			
			result.forEach(System.out::println);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
