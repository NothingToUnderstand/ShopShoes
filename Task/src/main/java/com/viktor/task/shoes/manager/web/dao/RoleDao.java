package com.viktor.task.shoes.manager.web.dao;

import com.viktor.task.shoes.manager.web.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {

}
