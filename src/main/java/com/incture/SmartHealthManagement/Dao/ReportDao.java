package com.incture.SmartHealthManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.Report;

@Repository
public interface ReportDao extends JpaRepository<Report, Long>
{

}
