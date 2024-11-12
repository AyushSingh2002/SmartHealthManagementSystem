package com.incture.SmartHealthManagement.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.incture.SmartHealthManagement.Dao.RoleDao;
import com.incture.SmartHealthManagement.Entities.Role;

@Service
public class RoleService 
{
	@Autowired
	RoleDao roleDao;
	
	public ResponseEntity<String> addRole(Role role)
	{
		roleDao.save(role);
		return new ResponseEntity<String>("Role added in database!", HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteRole(Long id)
	{
		Optional<Role> optional = roleDao.findById(id);
		if(optional.isPresent())
		{
			roleDao.deleteById(id);
			return new ResponseEntity<String>("Role removed from database!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Role not found!", HttpStatus.NOT_FOUND);
		}
	}
}
