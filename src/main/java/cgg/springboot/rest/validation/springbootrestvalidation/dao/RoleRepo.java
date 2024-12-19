package cgg.springboot.rest.validation.springbootrestvalidation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.springboot.rest.validation.springbootrestvalidation.entities.Role;

public interface RoleRepo  extends JpaRepository<Role, Integer>{

}
