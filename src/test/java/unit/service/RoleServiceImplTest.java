package unit.service;

import com.ednach.model.Role;
import com.ednach.repository.RoleRepository;
import com.ednach.service.impl.RoleServiceImpl;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTest {

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    @Test
    public void testFindAll() {
        final List<Role> roleList = Collections.singletonList(new Role());
        when(roleRepository.findAll()).thenReturn(roleList);
        assertEquals(roleService.findAll(), roleList);
    }

    @Test
    public void testFindById() {
        final Role role = new Role();
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        assertEquals(roleService.findById(1L), role);
    }

    @Test
    public void testSave() {
        final Role role = new Role();
        when(roleRepository.saveAndFlush(role)).thenReturn(role);
        assertEquals(roleService.save(role), role);
    }

    @Test
    public void testUpdate() {
        final Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        when(roleRepository.saveAndFlush(role)).thenReturn(role);
        assertEquals(roleService.update(role), role);
    }

    @Test
    public void testDelete() {
        final Role role = new Role();
        role.setId(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        doNothing().when(roleRepository).delete(role);
        assertDoesNotThrow(() -> roleService.delete(role));
    }

    @Test
    public void testDeleteById() {
        final Role role = new Role();
        role.setId(1L);
        doNothing().when(roleRepository).deleteById(1L);
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));
        assertDoesNotThrow(() -> roleService.deleteById(1L));
    }
}

