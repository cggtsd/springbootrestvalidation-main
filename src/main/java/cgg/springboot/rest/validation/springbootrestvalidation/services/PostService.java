package cgg.springboot.rest.validation.springbootrestvalidation.services;

import java.util.List;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.PostDto;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Post;

public interface PostService {
	//create
	PostDto createPost(PostDto postDto,Integer userId,Integer categoryId);
	//update
	Post updatePost (PostDto postDto,Integer postId);
	//delete
	void deletePost(Integer postId);
	//get all posts
	List<Post> getAllPost();
	//get singel post
	Post getPostById(Integer postId);
	//get all posts by category 
	List<Post> getPostsByCategory(Integer categoryId);
	//get all posts by user
	List<Post> getPostsByUser(Integer userId);
	//search  posts
	List<Post> searchPost(String keyword);

}
