package cgg.springboot.rest.validation.springbootrestvalidation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.springboot.rest.validation.springbootrestvalidation.entities.Category;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Post;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;

public interface PostRepo  extends JpaRepository<Post, Integer>{

	List<Post> findByUser(User1 user);
	List<Post> findByCategory(Category category);
}
