package cgg.springboot.rest.validation.springbootrestvalidation.services.impl;

import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cgg.springboot.rest.validation.springbootrestvalidation.dao.CategoryRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.PostRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.UserRepository;
import cgg.springboot.rest.validation.springbootrestvalidation.dto.PostDto;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Category;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Post;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;
import cgg.springboot.rest.validation.springbootrestvalidation.exceptions.ResourceNotFoundException;
import cgg.springboot.rest.validation.springbootrestvalidation.services.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepo postRepo;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Override
	public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId) {
		User1 user=this.userRepo.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Category category =this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category id",categoryId));

				Post post=this.modelMapper.map(postDto,Post.class);
				post.setImageName("default.png");
				post.setAddedDate(new Date());
				post.setUser(user);
				post.setCategory(category);
				Post newPost=this.postRepo.save(post);
				return this.modelMapper.map(newPost,PostDto.class);
	}

	@Override
	public Post updatePost(PostDto postDto, Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deletePost(Integer postId) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Post> getAllPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Post getPostById(Integer postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByCategory(Integer categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> getPostsByUser(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Post> searchPost(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

}
