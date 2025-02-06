package cgg.springboot.rest.validation.springbootrestvalidation.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import cgg.springboot.rest.validation.springbootrestvalidation.entities.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
