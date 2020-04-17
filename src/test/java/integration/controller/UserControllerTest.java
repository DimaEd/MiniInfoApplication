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
public class UserControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetNameExist() throws Exception {
        mockMvc.perform(get("/user/Tom"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Tom"))
                .andExpect(jsonPath("$.email").value("tom@mail.ru"))
                .andReturn();
    }

    @Test
    public void testGetNameNotExist() throws Exception {
        mockMvc.perform(get("/user/Alex"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }


    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/user"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Tom"))
                .andExpect(jsonPath("$[1].name").value("Peter"))
                .andReturn();
    }

    @Test
    public void testUpdateOneExist() throws Exception {
        mockMvc.perform(put("/user/2").contentType(APPLICATION_JSON_UTF8).content("{\"id\":2,\"name\":\"alex\",\"password\":\"43212\",\"email\":\"alex@mail.ru\",\"roleIds\":[1]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateNotExistBadRequest() throws Exception {
        mockMvc.perform(put("/user/5").contentType(APPLICATION_JSON_UTF8).content("{\"id\":1,\"name\":\"Alex\"}"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    @Test
    public void testSaveNotExist() throws Exception {
        mockMvc.perform(post("/user").contentType(APPLICATION_JSON_UTF8).content("{\"name\":\"alex\",\"password\":\"43212\",\"email\":\"alex@mail.ru\",\"roleIds\":[1]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("alex"))
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/user/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteNotExist() throws Exception {
        mockMvc.perform(delete("/user/5"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }
}
