package unit.controller;

import com.ednach.controller.UserController;
import com.ednach.dto.response.UserResponseDto;
import com.ednach.model.Role;
import com.ednach.model.User;
import com.ednach.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserControllerUnitTest {
    @Mock
    UserServiceImpl userService;
    @InjectMocks
    UserController userController;
    @Test
    void getAll() {
//        Role role = new Role(1L,"client");
//        User user1 = new User(1L,"Tom","tom@mail.ru",role);
//        User user2 = new User(2L,"Kate","kate@mail.ru",role);
//        List<User> users1 = new ArrayList<>();
        final List<User> userList = Collections.singletonList(new User());
        final List<UserResponseDto> userList2 = Collections.singletonList(new UserResponseDto());
//        users1.addAll(Arrays.asList(user1, user2));
//        User user3 = new User(1L,"Tom");
//        User user4 = new User(2L,"Kate");
//        List<User> users2 = new ArrayList<>();
//        users2.addAll(Arrays.asList(user3,user4));

        when(userService.findAll()).thenReturn(userList);
        assertEquals(userController.getAll(), userList2);
    }

    @Test
    void getName() {
    }

    @Test
    void save() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }


}
