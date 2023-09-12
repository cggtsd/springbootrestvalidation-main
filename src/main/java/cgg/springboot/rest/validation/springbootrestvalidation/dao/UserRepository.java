package cgg.springboot.rest.validation.springbootrestvalidation.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;

public interface UserRepository  extends JpaRepository<User1,Integer>{
    
	Optional<User1> findByEmail(String email);
}
