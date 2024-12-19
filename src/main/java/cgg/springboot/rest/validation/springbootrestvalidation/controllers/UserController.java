package cgg.springboot.rest.validation.springbootrestvalidation.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cgg.springboot.rest.validation.springbootrestvalidation.dto.UserDto;
import cgg.springboot.rest.validation.springbootrestvalidation.payload.ApiResponse;
import cgg.springboot.rest.validation.springbootrestvalidation.services.UserService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin("*")
public class UserController {
    
    @Autowired
    private UserService userService;

    //Get- user get
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> list =userService.getAllUsers();
        return new ResponseEntity<>(list,HttpStatus.OK);
    }

    //Get - single user
    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int userId){
           return ResponseEntity.ok(this.userService.getUserById(userId));
           
    }

    //Post -create user
    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = this.userService.createUser(userDto);
        
        return new ResponseEntity<UserDto>(createdUserDto,HttpStatus.CREATED);
    }
    
    //Put update -user
    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable int userId){
    	UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }
    
    //ADMIN
    //Delete - delete user
    @DeleteMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int userId) {
    	this.userService.deleteUser(userId);
    	
    	return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted successfully",true),HttpStatus.OK);
    }

}
