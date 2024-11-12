package com.incture.SmartHealthManagement.Dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Long>
{

	Optional<Role> findByName(String role);

}
