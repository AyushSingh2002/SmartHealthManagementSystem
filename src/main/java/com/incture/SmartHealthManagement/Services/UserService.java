package com.incture.SmartHealthManagement.Services;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.incture.SmartHealthManagement.Dao.RoleDao;
import com.incture.SmartHealthManagement.Dao.UserDao;
import com.incture.SmartHealthManagement.Entities.Role;
import com.incture.SmartHealthManagement.Entities.User;

@Service
public class UserService 
{
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private RoleDao roleDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public ResponseEntity<String> addUser(User user, Set<String> roleNames)
	{
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Set<Role> roles = new HashSet<Role>();
		for(String role : roleNames)
		{
			Optional<Role> optional = roleDao.findByName(role);
			optional.ifPresent(roles::add);
		}
		user.setRoles(roles);
		userDao.save(user);
		return new ResponseEntity<String>("User created successfully!", HttpStatus.OK);
	}
	
	public ResponseEntity<User> getUser(Long id)
	{
		Optional<User> optionalUser = userDao.findById(id);
		if(optionalUser.isPresent())
		{
			User user = optionalUser.get();
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<List<User>> getAllUsers()
	{
		List<User> users = userDao.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	public ResponseEntity<String> modifyUser(Long id, User updatedUser)
	{
		Optional<User> optionalUser = userDao.findById(id);
		if(optionalUser.isPresent())
		{
			User user = optionalUser.get();
			user.setUserName(updatedUser.getUserName());
			user.setPassword(updatedUser.getPassword());
			user.setEmail(updatedUser.getEmail());
			user.setRoles(updatedUser.getRoles());
			userDao.save(user);
			return new ResponseEntity<String>("User details updated successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("User id not found!", HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<String> deleteUser(Long id)
	{
		Optional<User> optionalUser = userDao.findById(id);
		if(optionalUser.isPresent())
		{
			userDao.deleteById(id);
			return new ResponseEntity<String>("User deleted successfully!", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("User id not found!", HttpStatus.NOT_FOUND);
		}
	}
}
