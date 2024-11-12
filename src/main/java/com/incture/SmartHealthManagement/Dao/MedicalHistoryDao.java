package com.incture.SmartHealthManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.MedicalHistory;

@Repository
public interface MedicalHistoryDao extends JpaRepository<MedicalHistory, Long>
{

}
