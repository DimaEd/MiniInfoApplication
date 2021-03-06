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
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
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

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    public void testFindAll() {
        final List<User> userList = Collections.singletonList(new User());
        when(userRepository.findAll()).thenReturn(userList);
        assertEquals(userService.findAll(), userList);
    }

    @Test
    public void testFindById() {
        final User user = new User();
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertEquals(userService.findById(1L), user);
    }

    @Test
    public void testSave() {
        final User user = new User();
        final Role role = new Role(1L);
        final Set<Role> roles = Collections.singleton(role);
        user.setRoles(roles);
        when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);
        when(roleService.findById(anyLong())).thenReturn(role);
        assertEquals(user, userService.save(user));
    }

    @Test
    public void testUpdate() {
        final User user = new User();
        final Set<Role> roles = new HashSet<>();
        final Role role = new Role(1L);
        roles.add(role);
        user.setId(1L);
        user.setRoles(roles);

        when(userRepository.saveAndFlush(user)).thenReturn(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(roleService.findById(1L)).thenReturn(role);
        assertEquals(userService.update(user), user);
    }

    @Test
    public void testDelete() {
        final User user = new User();
        user.setId(1L);
        doNothing().when(userRepository).delete(user);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        assertDoesNotThrow(() -> userService.delete(user));
    }

    @Test
    public void testDeleteById() {
        final User user = new User();
        user.setId(1L);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).deleteById(1L);
        assertDoesNotThrow(() -> userService.deleteById(1L));
    }
}