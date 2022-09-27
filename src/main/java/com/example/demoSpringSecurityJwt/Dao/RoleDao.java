package com.example.demoSpringSecurityJwt.Dao;

import com.example.demoSpringSecurityJwt.Entity.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<Role, String> {

}

