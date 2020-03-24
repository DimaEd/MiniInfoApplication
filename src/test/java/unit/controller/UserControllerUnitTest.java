package unit.controller;

import com.ednach.controller.UserController;
import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.User;
import com.ednach.service.impl.UserServiceImpl;
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
class UserControllerUnitTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Spy
    Mapper mapper = new DozerBeanMapper();

    final User user = new User();
    final UserResponseDto userResponseDto = new UserResponseDto();

    @Test
    void getAll() {
        List<UserResponseDto> userResponseDtoList = singletonList(userResponseDto);
        ResponseEntity<List<UserResponseDto>> userResponseDtoResponseEntity = new ResponseEntity<>(userResponseDtoList, HttpStatus.OK);
        List<User> users = singletonList(user);
        when(userService.findAll()).thenReturn(users);
        assertEquals(userController.getAll(), userResponseDtoResponseEntity);
    }

    @Test
    void getName() {
        ResponseEntity<UserResponseDto> userResponseDtoResponseEntity = new ResponseEntity(userResponseDto, HttpStatus.OK);
        when(userService.findByName(any(String.class))).thenReturn(user);
        assertEquals(userController.getName("alex"), userResponseDtoResponseEntity);
    }

    @Test
    void save() {
        ResponseEntity<UserResponseDto> userResponseDtoResponseEntity = new ResponseEntity(userResponseDto, HttpStatus.OK);
        UserRequestDto userRequestDto = new UserRequestDto();
        when(userService.save(any())).thenReturn(user);
        assertEquals(userController.save(userRequestDto), userResponseDtoResponseEntity);
    }

    @Test
    void update() {
        ResponseEntity<UserResponseDto> userResponseDtoResponseEntity = new ResponseEntity(userResponseDto, HttpStatus.OK);
        UserRequestDto userRequestDto = new UserRequestDto();
        when(userService.update(any())).thenReturn(user);
        assertEquals(userController.update(userRequestDto, 1L), userResponseDtoResponseEntity);
    }

    @Test
    void delete() {
        doNothing().when(userService).deleteById(any(Long.class));
        assertDoesNotThrow(() -> userController.delete(1L));
    }

}



