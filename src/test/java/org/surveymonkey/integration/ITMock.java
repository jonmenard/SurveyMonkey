package org.surveymonkey.integration;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.surveymonkey.models.EndUser;
import org.surveymonkey.services.EndUserService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ITMock {

    private static final String HEADERFILES_FRAGMENT = "-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7";
    private static final String HEADER_FRAGMENT = "circular green edit outline icon";

    @Autowired
    private MockMvc mockMvc;

    @Mock
    EndUserService endUserService;

    @Test
    public void testErrorTemplate() throws Exception {
        mockMvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Template name is error")))
                .andExpect(content().string(containsString(HEADERFILES_FRAGMENT)))
                .andExpect(content().string(containsString(HEADER_FRAGMENT)));
    }

    @Test
    public void testIndexTemplate() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Template name is index")))
                .andExpect(content().string(containsString(HEADERFILES_FRAGMENT)))
                .andExpect(content().string(containsString(HEADER_FRAGMENT)));
    }

    @Test
    public void testCreateUserTemplate() throws Exception {
        mockMvc.perform(get("/index/create"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Template name is createUser")))
                .andExpect(content().string(containsString(HEADERFILES_FRAGMENT)))
                .andExpect(content().string(containsString(HEADER_FRAGMENT)));
    }

    @Test
    public void testUserLogin() throws Exception {
        EndUser user = new EndUser();//whichever data your entity class have
        user.setName("testuser");
        Mockito.when(endUserService.save(Mockito.any(EndUser.class))).thenReturn(user);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .param("name",user.getName()))
                .andExpect(status().isFound());
    }

}