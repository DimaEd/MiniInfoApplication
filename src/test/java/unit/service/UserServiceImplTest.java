package unit.service;

import com.ednach.model.Role;
import com.ednach.model.User;
import com.ednach.repository.UserRepository;
import com.ednach.service.RoleService;
import com.ednach.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    final User user = new User();
    final Role role = new Role(1L,"client");

    @Test
    public void testFindAll() {
        final List<User> userList = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userService.findAll(), userList);
    }

    @Test
    public void testFindById() {
        when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
        assertEquals(userService.findById(1L), user);
    }

    @Test
    public void testSave() {
        user.setRole(role);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(roleService.findById(1L)).thenReturn(role);
        assertEquals(userService.save(user), user);
    }

    @Test
    public void testUpdate() {
        user.setId(1L);
        user.setRole(role);
        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roleService.findById(1L)).thenReturn(role);
        assertEquals(userService.update(user), user);
    }

    @Test
    public void testDelete() {
        user.setId(1L);
        doNothing().when(userRepository).delete(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> userService.delete(user));
    }

    @Test
    public void testDeleteById() {
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(any(Long.class));
        assertDoesNotThrow(() -> userService.deleteById(1L));
    }
}