package com.ednach.controller;

import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.Role;
import com.ednach.model.User;
import com.ednach.service.UserService;
import com.ednach.service.impl.UserServiceImpl;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")

public class UserController {
    private final Mapper mapper;

    private final UserService userService;

    public UserController(Mapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<UserResponseDto>> getAll() {
        final List<User> users = userService.findAll();
        final List<UserResponseDto> userResponseDtoList = users.stream()
                .map((user) -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{name}", method = RequestMethod.GET)
    public ResponseEntity<UserResponseDto> getName(@PathVariable String name) {
        final UserResponseDto userResponseDto = mapper.map(userService.findByName(name), UserResponseDto.class);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<UserResponseDto> save(@Valid @RequestBody UserRequestDto userRequestDto) {
        userRequestDto.setId(null);
        final UserResponseDto userResponseDto = mapper.map(userService.save(getUser(userRequestDto)), UserResponseDto.class);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserResponseDto> update(@Valid @RequestBody UserRequestDto userRequestDto, @PathVariable Long id) {
        final UserResponseDto userResponseDto = mapper.map(userService.update(getUser(userRequestDto)), UserResponseDto.class);
        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }


    private User getUser(UserRequestDto userRequestDto) {
        final User user = mapper.map(userRequestDto, User.class);
        final Set<Role> roles = userRequestDto.getRoleIds().stream().map(roleId -> {
            Role role = new Role();
            role.setId(roleId);
            return role;
        }).collect(Collectors.toSet());
        user.setRoles(roles);
        return user;
    }
}