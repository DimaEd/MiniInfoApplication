package com.ednach.repository;

import com.ednach.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {
     @Query("SELECT DISTINCT r from Role r join r.users")
    List<Role> findAll();

    Role findByRoleName(String roleName);
}
