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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(loader = AnnotationConfigWebContextLoader.class, classes = {TestWebConfiguration.class, DatabaseConfiguration.class})
@WebAppConfiguration
@Transactional
public class ProducerControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/producer"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("Nokia"))
                .andExpect(jsonPath("$[1].companyName").value("Samsung"))
                .andReturn();
    }

        @Test
    public void testGetCompanyNameExist() throws Exception {
        mockMvc.perform(get("/producer/Samsung"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].companyName").value("Samsung"))
                .andReturn();
    }

    @Test
    public void testGetCompanyNameNotExist() throws Exception {
        mockMvc.perform(get("/producer/Wik"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    public void testUpdateOneExist() throws Exception {
        mockMvc.perform(put("/producer/3").contentType(APPLICATION_JSON_UTF8).content("{\"id\":3,\"companyName\":\"Sony\",\"email\":\"sony@mail.ru\",\"country\":\"Russia\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateNotExistBadRequest() throws Exception {
        mockMvc.perform(put("/producer/5").contentType(APPLICATION_JSON_UTF8).content("{\"id\":5,\"companyName\":\"Sony\",\"email\":\"sony@mail.ru\",\"country\":\"Russia\"}"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    @Test
    public void testSaveNotExist() throws Exception {
        mockMvc.perform(post("/producer").contentType(APPLICATION_JSON_UTF8).content("{\"companyName\":\"Sony\",\"email\":\"sony@mail.ru\",\"country\":\"Russia\"}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.companyName").value("Sony"))
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/producer/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteNotExist() throws Exception {
        mockMvc.perform(delete("/producer/5"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }
}
