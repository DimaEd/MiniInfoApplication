package integration.controller;

import com.ednach.config.DatabaseConfiguration;
import com.ednach.config.SecurityConfiguration;
import integration.configuration.TestWebConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.AnnotationConfigWebContextLoader;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class, DatabaseConfiguration.class})
@WebAppConfiguration
@Transactional

public class AuthenticationControllerTest {

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

@BeforeEach
public void setup() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
}


    @Test
    public void testSignInExist() throws Exception {
        mockMvc.perform(post("/authentication/signIn").contentType(APPLICATION_JSON_UTF8).content("{\"username\":\"Tom\",\"password\":\"1234\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testSignInNotExist() throws Exception {
        mockMvc.perform(post("/authentication/signIn").contentType(APPLICATION_JSON_UTF8).content("{\"username\":\"Tom\",\"password\":\"43212\"}"))
                .andDo(print())
                .andExpect(status().is(500))
                .andReturn();
    }

    @Test
    public void testSignUp() throws Exception {
        mockMvc.perform(post("/authentication/signUp").contentType(APPLICATION_JSON_UTF8).content("{\"name\":\"Alex\",\"password\":\"43212\",\"email\":\"alex@mail.ru\",\"roles\":[\"ROLE_USER\"]}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Alex"))
                .andExpect(jsonPath("$.email").value("alex@mail.ru"))
                .andReturn();
    }


























}
