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
public class ProductControllerTest {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetProductName() throws Exception {
        mockMvc.perform(get("/product/samsung"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("samsung"))
                .andReturn();
    }
    @Test
    public void testGetProductNameNotExist() throws Exception {
        mockMvc.perform(get("/product/Sony"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }


    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get("/product"))
                .andDo(print())
                .andExpect(status().isOk())
               .andExpect(jsonPath("$[2].productName").value("samsung"))
                .andExpect(jsonPath("$[0].productName").value("apple"))
                .andReturn();
    }
    @Test
    public void testUpdateOneExist() throws Exception {
        mockMvc.perform(put("/product/3").contentType(APPLICATION_JSON_UTF8).content("{\"id\":3,\"productName\":\"sony\",\"cost\":10000,\"userId\":1}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testUpdateNotExistBadRequest() throws Exception {
        mockMvc.perform(put("/product/7").contentType(APPLICATION_JSON_UTF8).content("{\"id\":10,\"productName\":\"sony\",\"cost\":10000,\"userId\":1}"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }

    @Test
    public void testSaveNotExist() throws Exception {
        mockMvc.perform(post("/product").contentType(APPLICATION_JSON_UTF8).content("{\"productName\":\"sony\",\"cost\":10000,\"userId\":1}"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productName").value("sony"))
                .andReturn();
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete("/product/2"))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
    }

    @Test
    public void testDeleteNotExist() throws Exception {
        mockMvc.perform(delete("/product/5"))
                .andDo(print())
                .andExpect(status().is5xxServerError())
                .andReturn();
    }
}
