package com.incture.SmartHealthManagement.Controllers;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.incture.SmartHealthManagement.Entities.User;
import com.incture.SmartHealthManagement.Services.UserService;

@RestController
@RequestMapping("/api/v1/User")
public class UserController 
{
	@Autowired
	UserService userService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@PostMapping("add-user")
	public ResponseEntity<String> addUser(@RequestBody User user, @RequestParam Set<String> roles)
	{
		LOGGER.info("add-user endpoint used.");
		return userService.addUser(user, roles);
	}
	
	@GetMapping("/get-user/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id)
	{
		LOGGER.info("get-user endpoint used.");
		return userService.getUser(id);
	}
	
	@GetMapping("/get-all-users")
	public ResponseEntity<List<User>> getAllUsers()
	{
		LOGGER.info("get-all-users endpoint used.");
		return userService.getAllUsers();
	}
	
	@PutMapping("/update-user/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user)
	{
		LOGGER.info("update-user endpoint used.");
		return userService.modifyUser(id, user);
	}
	
	@DeleteMapping("/delete-user/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id)
	{
		LOGGER.info("delete-user endpoint used.");
		return userService.deleteUser(id);
	}
}
