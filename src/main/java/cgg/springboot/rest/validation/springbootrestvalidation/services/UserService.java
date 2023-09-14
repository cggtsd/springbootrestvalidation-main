package cgg.springboot.rest.validation.springbootrestvalidation.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cgg.springboot.rest.validation.springbootrestvalidation.config.AppConstants;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.RoleRepo;
import cgg.springboot.rest.validation.springbootrestvalidation.dao.UserRepository;
import cgg.springboot.rest.validation.springbootrestvalidation.dto.UserDto;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.Role;
import cgg.springboot.rest.validation.springbootrestvalidation.entities.User1;
import cgg.springboot.rest.validation.springbootrestvalidation.exceptions.ResourceNotFoundException;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private RoleRepo roleRepo;

    public List<UserDto> getAllUsers() {
        List<User1> users=userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user->modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    public UserDto getUserById(int userId) {
    User1 user=	this.userRepository.findById(userId)
       	     .orElseThrow(()->new ResourceNotFoundException("user ","id ",userId));	
        return modelMapper.map(user, UserDto.class);
    }

    public UserDto registerNewUser(UserDto userDto) {
    	User1 user = modelMapper.map(userDto, User1.class);
    	
    	//encoded the password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    
        //roles
        Role role = this.roleRepo.findById(AppConstants.ROLE_NORMAL).get();
    
        user.getRoles().add(role);
        
        User1 newUser = this.userRepository.save(user);
        
        return this.modelMapper.map(newUser, UserDto.class);
    
    }
    
    public UserDto createUser(UserDto userDto) {
    	User1 user = this.modelMapper.map(userDto, User1.class);
        User1 savedUser = this.userRepository.save(user);
        return this.modelMapper.map(savedUser, UserDto.class);
    }
    
    public UserDto updateUser(UserDto userDto,Integer userId) {
    User1 user=	this.userRepository.findById(userId)
    	     .orElseThrow(()->new ResourceNotFoundException("user ","id ",userId));
    
    user.setName(userDto.getName());
    user.setEmail(userDto.getEmail());
    user.setPassword(userDto.getPassword());
    user.setAbout(userDto.getAbout());
    
    User1 updatedUser=this.userRepository.save(user);
    
    return this.modelMapper.map(updatedUser, UserDto.class);
    
    }
    
    public void deleteUser(Integer userId) {
    	User1 user=	this.userRepository.findById(userId)
       	     .orElseThrow(()->new ResourceNotFoundException("user ","id ",userId));
    	this.userRepository.delete(user);
    	
    }

}
