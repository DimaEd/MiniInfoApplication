package com.ednach.service;

import com.ednach.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role findById (Long id);

    Role findByRoleNome(String roleName);

    Role save(Role role);

    Role update(Role role);

    void delete(Role role);

    void deleteById(Long id);

}
