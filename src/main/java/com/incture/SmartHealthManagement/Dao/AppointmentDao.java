package com.incture.SmartHealthManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Long>
{

}
