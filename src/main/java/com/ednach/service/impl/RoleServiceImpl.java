package com.ednach.service.impl;

import com.ednach.model.Role;
import com.ednach.repository.RoleRepository;
import com.ednach.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of service interface for Role entity
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findAllByRole(Long id) {
        return roleRepository.findById(id).orElseThrow(()-> new RuntimeException("Ohh"));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public Role update(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void delete(Role role) {
        roleRepository.delete(role);

    }

    @Override
    public void deleteById(Long id) {
        roleRepository.deleteById(id);
    }


}
