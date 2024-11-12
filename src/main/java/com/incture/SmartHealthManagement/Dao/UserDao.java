package com.incture.SmartHealthManagement.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.incture.SmartHealthManagement.Entities.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>
{

}
