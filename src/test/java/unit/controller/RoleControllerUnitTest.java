package unit.controller;

import com.ednach.controller.RoleController;
import com.ednach.dto.RoleDto;
import com.ednach.model.Role;
import com.ednach.service.impl.RoleServiceImpl;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RoleControllerUnitTest {

    @InjectMocks
    RoleController roleController;

    @Mock
    RoleServiceImpl roleService;

    @Spy
    Mapper mapper = new DozerBeanMapper();

    @Test
    void getAll() {
        final Role role = new Role();
        final RoleDto roleDto = new RoleDto();
        List<RoleDto> roleDtoList = singletonList(roleDto);
        ResponseEntity<List<RoleDto>> roleDtoResponseEntity = new ResponseEntity<>(roleDtoList, HttpStatus.OK);
        List<Role> roles = singletonList(role);
        when(roleService.findAll()).thenReturn(roles);
        assertEquals(roleController.getAll(), roleDtoResponseEntity);
    }

    @Test
    void getOne() {
        final Role role = new Role();
        final RoleDto roleDto = new RoleDto();
        ResponseEntity<RoleDto> roleDtoResponseEntity = new ResponseEntity(roleDto, HttpStatus.OK);
        when(roleService.findById(1L)).thenReturn(role);
        assertEquals(roleController.getOne(1L), roleDtoResponseEntity);
    }

    @Test
    void save() {
        final Role role = new Role();
        final RoleDto roleDto = new RoleDto();
        ResponseEntity<RoleDto> roleDtoResponseEntity = new ResponseEntity(roleDto, HttpStatus.OK);
        RoleDto roleRequest = new RoleDto();
        when(roleService.save(any(Role.class))).thenReturn(role);
        assertEquals(roleController.save(roleRequest), roleDtoResponseEntity);
    }

    @Test
    void update() {
        final Role role = new Role();
        final RoleDto roleDto = new RoleDto();
        ResponseEntity<RoleDto> roleDtoResponseEntity = new ResponseEntity(roleDto, HttpStatus.OK);
        RoleDto roleRequestDto = new RoleDto();
        when(roleService.update(any(Role.class))).thenReturn(role);
        assertEquals(roleController.update(roleRequestDto, 1L), roleDtoResponseEntity);

    }

    @Test
    void delete() {
        doNothing().when(roleService).deleteById(1L);
        assertDoesNotThrow(() -> roleController.delete(1L));
    }

}