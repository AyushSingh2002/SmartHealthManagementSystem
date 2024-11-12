package com.incture.SmartHealthManagement.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.incture.SmartHealthManagement.Dao.RoleDao;
import com.incture.SmartHealthManagement.Dao.UserDao;
import com.incture.SmartHealthManagement.Entities.Role;
import com.incture.SmartHealthManagement.Entities.User;

class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserDao userDao;

    @Mock
    private RoleDao roleDao;

    @Mock
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddUser() {
        User user = new User();
        user.setPassword("plaintextPassword");

        Set<String> roleNames = new HashSet<>(Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
        Role userRole = new Role(1L, "ROLE_USER");
        Role adminRole = new Role(2L, "ROLE_ADMIN");

        when(roleDao.findByName("ROLE_USER")).thenReturn(Optional.of(userRole));
        when(roleDao.findByName("ROLE_ADMIN")).thenReturn(Optional.of(adminRole));
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        ResponseEntity<String> response = userService.addUser(user, roleNames);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User created successfully!", response.getBody());
        verify(userDao, times(1)).save(user);
        assertEquals("encodedPassword", user.getPassword());
        assertEquals(2, user.getRoles().size());
    }

    @Test
    void testGetUser_UserExists() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);

        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<User> response = userService.getUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    void testGetUser_UserNotFound() {
        Long userId = 1L;

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<User> response = userService.getUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllUsers() {
        List<User> users = Arrays.asList(new User(), new User());

        when(userDao.findAll()).thenReturn(users);

        ResponseEntity<List<User>> response = userService.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(users, response.getBody());
    }

    @Test
    void testModifyUser_UserExists() {
        Long userId = 1L;
        User existingUser = new User();
        existingUser.setId(userId);
        User updatedUser = new User();
        updatedUser.setUserName("newUserName");
        updatedUser.setPassword("newPassword");
        updatedUser.setEmail("newEmail@example.com");
        Set<Role> newRoles = new HashSet<>();
        newRoles.add(new Role(1L, "ROLE_USER"));
        updatedUser.setRoles(newRoles);

        when(userDao.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userDao.save(any(User.class))).thenReturn(existingUser);

        ResponseEntity<String> response = userService.modifyUser(userId, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User details updated successfully!", response.getBody());
        assertEquals("newUserName", existingUser.getUserName());
        assertEquals("newPassword", existingUser.getPassword());
        assertEquals("newEmail@example.com", existingUser.getEmail());
        assertEquals(1, existingUser.getRoles().size());
        verify(userDao, times(1)).save(existingUser);
    }

    @Test
    void testModifyUser_UserNotFound() {
        Long userId = 1L;
        User updatedUser = new User();

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = userService.modifyUser(userId, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User id not found!", response.getBody());
        verify(userDao, never()).save(any(User.class));
    }

    @Test
    void testDeleteUser_UserExists() {
        Long userId = 1L;
        User user = new User();

        when(userDao.findById(userId)).thenReturn(Optional.of(user));

        ResponseEntity<String> response = userService.deleteUser(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("User deleted successfully!", response.getBody());
        verify(userDao, times(1)).deleteById(userId);
    }

    @Test
    void testDeleteUser_UserNotFound() {
        Long userId = 1L;

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = userService.deleteUser(userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User id not found!", response.getBody());
        verify(userDao, never()).deleteById(anyLong());
    }
}
