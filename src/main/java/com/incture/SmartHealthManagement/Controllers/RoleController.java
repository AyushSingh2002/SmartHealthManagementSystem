package com.incture.SmartHealthManagement.Controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.incture.SmartHealthManagement.Entities.Role;
import com.incture.SmartHealthManagement.Services.RoleService;

@RestController
@RequestMapping("/api/v1/Role")
public class RoleController 
{
	@Autowired
	RoleService roleService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
	
	@PostMapping("/add-role")
	public ResponseEntity<String> addRole(@RequestBody Role role)
	{
		LOGGER.info("add-role endpoint used.");
		return roleService.addRole(role);
	}
	
	@DeleteMapping("/delete-role/{id}")
	public ResponseEntity<String> deleteRole(@PathVariable Long id)
	{
		LOGGER.info("delete-role endpoint used.");
		return roleService.deleteRole(id);
	}
}
