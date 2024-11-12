package com.incture.SmartHealthManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.Prescription;

@Repository
public interface PrescriptionDao extends JpaRepository<Prescription, Long>
{

}
