package unit.controller;

import com.ednach.config.DatabaseConfiguration;
import com.ednach.controller.UserController;
import com.ednach.dto.request.UserRequestDto;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.User;
import com.ednach.service.UserService;
import com.ednach.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import integration.configuration.TestWebConfiguration;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static java.util.Collections.singleton;
import static java.util.Collections.singletonList;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;

import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
class UserControllerUnitTest {

    @InjectMocks
    UserController userController;

    @Mock
    UserServiceImpl userService;

    @Spy
    Mapper mapper;

    @Test
    void getAll() throws Exception {
        UserResponseDto userResponseDto = new UserResponseDto();
        List<UserResponseDto> userResponseDtoList = singletonList(userResponseDto);
        User user = new User();
        List<User> users = singletonList(user);

        when(userService.findAll()).thenReturn(users);
       assertEquals(userController.getAll().getBody(), userResponseDtoList);
    }

    @Test
    void getName() {
//        when(userService.findByName(any(String.class))).thenReturn(user);
//        when(mapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);
//        assertEquals(userController.getName("alex").getBody(), userResponseDto);
//    }

//    @Test
//    void save() {
//        UserRequestDto userRequestDto = new UserRequestDto();
//        when(mapper.map(userRequestDto, User.class)).thenReturn(user);
//        when(userService.save(user)).thenReturn(user);
//       when(mapper.map(user, UserResponseDto.class)).thenReturn(userResponseDto);
//        assertEquals(userController.save(userRequestDto).getBody(), userResponseDto);
//    }

//    @Test
//    void update() {
//        UserRequestDto userRequestDto = new UserRequestDto();
//        when(mapper.map(userRequestDto, User.class)).thenReturn(user);
//        when(mapper.map(userService.update(userService.update(userController.getUser(userRequestDto))), UserResponseDto.class)).thenReturn(userResponseDto);
//        assertEquals(userController.update(userRequestDto,1L).getBody(), userResponseDto);
//
//    }

//    @Test
//    void delete() {
//        user.setId(1L);
//        doNothing().when(userService.deleteById(1L)).thenReturn(Optional.of(user));
//        assertDoesNotThrow(() -> userService.deleteById(1L));

    }


}
