package integration.controller;

import com.ednach.config.DatabaseConfiguration;
import integration.configuration.TestWebConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class, DatabaseConfiguration.class})
@WebAppConfiguration
@Transactional
public class RoleControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetOneExist() throws Exception {
        mockMvc.perform(get("/role/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value("client"))
                .andReturn();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/role"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].roleName").value("client"))
                .andExpect(jsonPath("$[1].roleName").value("admin"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void testUpdate() throws Exception {
        mockMvc.perform(put("/role/2").contentType(APPLICATION_JSON_UTF8).content("{\"id\":2,\"roleName\":\"user\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value("user"))
                .andReturn();
    }

    @Test
    public void testSave() throws Exception {
        mockMvc.perform(post("/role").contentType(APPLICATION_JSON_UTF8).content("{\"roleName\":\"user\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.roleName").value("user"))
                .andReturn();
    }

    @Test
    public void testDeleteExist() throws Exception {
        mockMvc.perform(get("/role/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
}
