package unit.controller;

import com.ednach.controller.UserController;
import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.User;
import com.ednach.service.impl.UserServiceImpl;
import org.dozer.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerUnitTest {
   
    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Mock
    Mapper mapper;
   private UserResponseDto userResponseDto = new UserResponseDto();
   private User user = new User();

    @BeforeEach
    void setUp() throws Exception {
    }


    @Test
    void getAll() {
        List<User> users = Arrays.asList(user);
        List<UserResponseDto> userResponseDtoList;
        when(userService.findAll()).thenReturn(users);
        userResponseDtoList = users.stream()
                .map((user) -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
        assertEquals(userController.getAll().getBody(),userResponseDtoList);
    }

    @Test
    void getName() {
        when(userService.findByName(any(String.class))).thenReturn(user);
        when(mapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);
        assertEquals(userController.getName("alex").getBody(), userResponseDto);
    }

    @Test
    void save() {
        UserRequestDto userRequestDto = new UserRequestDto();
        when(mapper.map(userRequestDto, User.class)).thenReturn(user);
        when(userService.save(user)).thenReturn(user);
       when(mapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);
        assertEquals(userController.save(userRequestDto).getBody(), userResponseDto);
    }

    @Test
    void update() {
        UserRequestDto userRequestDto = new UserRequestDto();
        when(mapper.map(userRequestDto, User.class)).thenReturn(user);
        when(mapper.map(userService.update(userService.update(userController.getUser(userRequestDto))), UserResponseDto.class)).thenReturn(userResponseDto);
        assertEquals(userController.update(userRequestDto,1L).getBody(), userResponseDto);

    }

    @Test
    void delete() {
//        user.setId(1L);
//        doNothing().when(userService.deleteById(1L)).thenReturn(Optional.of(user));
//        assertDoesNotThrow(() -> userService.deleteById(1L));

    }


}
