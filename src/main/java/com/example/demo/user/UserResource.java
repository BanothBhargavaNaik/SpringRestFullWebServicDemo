package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.exception.UserNotFoundException;

@RestController
@RequestMapping("/api/v1")
public class UserResource {

	private final UserDaoService service;

	public UserResource(UserDaoService service) {
		super();
		this.service = service;
	}

	@GetMapping("/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	@GetMapping("/user/{id}")
	public User retrieveUser(@PathVariable int id) {
	    User user =service.findOne(id);
	    if(user == null)
	    	throw new UserNotFoundException("id : "+id);
	    // "allUser" , server_path +"/users"
	    return user;
	}

	@PostMapping("/user")
	public ResponseEntity<Object> createdUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);
		
	URI location =	ServletUriComponentsBuilder
		.fromCurrentRequest()
		.path("/{id}")
		.buildAndExpand(savedUser.getId()).toUri();
		
		return ResponseEntity.created(location).build();
		
	}
	
	@DeleteMapping("/user/{id}")
	public void deletUser(@PathVariable int id) {
	    User user =service.deleteById(id);
	    if(user == null)
	    	throw new UserNotFoundException("id : "+id);
	}


}
