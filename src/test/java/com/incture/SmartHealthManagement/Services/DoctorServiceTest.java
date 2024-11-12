package com.incture.SmartHealthManagement.Services;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.incture.SmartHealthManagement.Dao.DoctorDao;
import com.incture.SmartHealthManagement.Dao.UserDao;
import com.incture.SmartHealthManagement.Entities.Doctor;
import com.incture.SmartHealthManagement.Entities.User;

class DoctorServiceTest {

    @InjectMocks
    private DoctorService doctorService;

    @Mock
    private DoctorDao doctorDao;

    @Mock
    private UserDao userDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddDoctor_UserExists() {
        Long userId = 1L;
        Doctor doctor = new Doctor();
        User user = new User();
        user.setId(userId);

        when(userDao.findById(userId)).thenReturn(Optional.of(user));
        when(doctorDao.save(doctor)).thenReturn(doctor);

        ResponseEntity<String> response = doctorService.addDoctor(doctor, userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Doctor profile created!", response.getBody());
        verify(doctorDao, times(1)).save(doctor);
    }

    @Test
    void testAddDoctor_UserNotFound() {
        Long userId = 1L;
        Doctor doctor = new Doctor();

        when(userDao.findById(userId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = doctorService.addDoctor(doctor, userId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("User not found!", response.getBody());
        verify(doctorDao, never()).save(doctor);
    }

    @Test
    void testGetDoctor_DoctorExists() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        when(doctorDao.findById(doctorId)).thenReturn(Optional.of(doctor));

        ResponseEntity<Doctor> response = doctorService.getDoctor(doctorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctor, response.getBody());
    }

    @Test
    void testGetDoctor_DoctorNotFound() {
        Long doctorId = 1L;

        when(doctorDao.findById(doctorId)).thenReturn(Optional.empty());

        ResponseEntity<Doctor> response = doctorService.getDoctor(doctorId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void testGetAllDoctors() {
        List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());

        when(doctorDao.findAll()).thenReturn(doctors);

        ResponseEntity<List<Doctor>> response = doctorService.getAllDoctors();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctors, response.getBody());
    }

    @Test
    void testUpdateDoctor_DoctorExists() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        Doctor updatedDoctor = new Doctor();
        updatedDoctor.setFirstName("UpdatedFirstName");
        
        when(doctorDao.findById(doctorId)).thenReturn(Optional.of(doctor));
        when(doctorDao.save(any(Doctor.class))).thenReturn(updatedDoctor);

        ResponseEntity<String> response = doctorService.updateDoctor(doctorId, updatedDoctor);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Doctor profile updated successfully!", response.getBody());
        verify(doctorDao, times(1)).save(doctor);
    }

    @Test
    void testUpdateDoctor_DoctorNotFound() {
        Long doctorId = 1L;
        Doctor updatedDoctor = new Doctor();

        when(doctorDao.findById(doctorId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = doctorService.updateDoctor(doctorId, updatedDoctor);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Doctor profile not found!", response.getBody());
        verify(doctorDao, never()).save(any(Doctor.class));
    }

    @Test
    void testDeleteDoctor_DoctorExists() {
        Long doctorId = 1L;
        Doctor doctor = new Doctor();
        
        when(doctorDao.findById(doctorId)).thenReturn(Optional.of(doctor));

        ResponseEntity<String> response = doctorService.deleteDoctor(doctorId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Doctor profile deleted successfully!", response.getBody());
        verify(doctorDao, times(1)).deleteById(doctorId);
    }

    @Test
    void testDeleteDoctor_DoctorNotFound() {
        Long doctorId = 1L;

        when(doctorDao.findById(doctorId)).thenReturn(Optional.empty());

        ResponseEntity<String> response = doctorService.deleteDoctor(doctorId);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Doctor profile not found!", response.getBody());
        verify(doctorDao, never()).deleteById(anyLong());
    }
}
