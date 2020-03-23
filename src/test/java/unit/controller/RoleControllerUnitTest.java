package unit.controller;

import com.ednach.controller.RoleController;
import com.ednach.model.Role;
import com.ednach.service.impl.RoleServiceImpl;
import integration.configuration.TestWebConfiguration;
import org.dozer.Mapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@WebAppConfiguration()
@ContextConfiguration(classes = {TestWebConfiguration.class})
public class RoleControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    RoleServiceImpl roleService;

    @Mock
     Mapper mapper;

    @InjectMocks
    RoleController roleController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(roleController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }


    @Test
    void getAll() throws Exception {
        Role role = new Role();
        role.setId(1L);
        role.setRoleName("client");
        List<Role> roles = new ArrayList<>();
        roles.add(role);
        given(roleService.findAll()).willReturn(roles);
            mockMvc.perform(get("/role")).andExpect(status().isOk())
            .andDo(print());




    }


}
