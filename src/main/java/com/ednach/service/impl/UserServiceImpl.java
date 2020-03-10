package com.ednach.service.impl;

import com.ednach.model.User;
import com.ednach.repository.UserRepository;
import com.ednach.service.RoleService;
import com.ednach.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Implementation of service interface for User entity
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    private RoleService roleService;

    public UserServiceImpl(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Don't remember"));
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User save(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User update(User user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    private User saveAndFlush(User user) {
        //  validate(user.getRole() == null || user.getRole().getId() == null, localizedMessageSource.getMessage("error.user.role.isNull", new Object[]{}));
      //  user.setRole(roleService.findById(user.getRole().getId()));
        return userRepository.saveAndFlush(user);
    }
}
