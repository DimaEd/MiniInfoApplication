package com.ednach.controller;

import com.ednach.dto.request.RoleRequestDto;
import com.ednach.dto.response.RoleResponseDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.Role;
import com.ednach.service.RoleService;
import org.dozer.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/role")
public class RoleController {
    private Mapper mapper;
    private RoleService roleService;

    public RoleController(Mapper mapper, RoleService roleService) {
        this.mapper = mapper;
        this.roleService = roleService;
    }
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<RoleResponseDto>> getAll() {
        final List<Role> roles = roleService.findAll();
        final List<RoleResponseDto> roleDtoList = roles.stream()
                .map((role) -> mapper.map(role, RoleResponseDto.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(roleDtoList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<RoleResponseDto> getName(@PathVariable Long id) {
        final RoleResponseDto roleResponseDto = mapper.map(roleService.findAllByRole(id), RoleResponseDto.class);
        return new ResponseEntity<>(roleResponseDto, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<RoleResponseDto> save(@Valid @RequestBody RoleRequestDto roleDto) {
        roleDto.setId(null);
        final RoleResponseDto responseRoleDto = mapper.map(roleService.save(mapper.map(roleDto, Role.class)), RoleResponseDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<RoleResponseDto> update(@Valid @RequestBody RoleResponseDto roleDto, @PathVariable Long id) {
//        if (!Objects.equals(id, roleDto.getId())) {
//            throw new RuntimeException(localizedMessageSource.getMessage("controller.role.unexpectedId", new Object[]{}));
//        }
        final RoleResponseDto responseRoleDto = mapper.map(roleService.update(mapper.map(roleDto, Role.class)), RoleResponseDto.class);
        return new ResponseEntity<>(responseRoleDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        roleService.deleteById(id);
    }
}
