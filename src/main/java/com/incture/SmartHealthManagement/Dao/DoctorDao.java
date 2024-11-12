package com.incture.SmartHealthManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.Doctor;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, Long>
{

}
