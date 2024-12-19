package cgg.springboot.rest.validation.springbootrestvalidation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.springboot.rest.validation.springbootrestvalidation.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
