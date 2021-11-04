package br.com.itau.selective.process.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static br.com.itau.selective.process.commons.constant.ApplicationConstant.Password.V1.PASSWORD_VALIDATE;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PasswordControllerIntegratedTest {

    @Autowired
    private MockMvc mockMvc;
    private HttpHeaders headers;
    @InjectMocks
    private PasswordController passwordController;

    @BeforeEach
    public void init() {
        headers = new HttpHeaders();
    }

    @Test
    public void shouldReturnCreatedWhenSendPasswordValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATE)
                        .contentType(APPLICATION_JSON)
                        .content(getUserRequestPasswordValid())
                        .headers(headers))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordValid").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordValid").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordValid").isBoolean())
                .andExpect(MockMvcResultMatchers.jsonPath("$.passwordValid").value("true"))
                .andReturn()
                .getResponse();
    }

    @Test
    public void shouldReturnPreconditionWhenSendPasswordValid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(PASSWORD_VALIDATE)
                        .contentType(APPLICATION_JSON)
                        .content(getUserRequestPasswordNotUpperCase())
                        .headers(headers))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn()
                .getResponse();
    }

    private String getUserRequestPasswordValid() {
        return "{\n" +
                "    \"password\":\"AbTp9!fok\"\n" +
                "}";
    }

    private String getUserRequestPasswordNotUpperCase() {
        return "{\n" +
                "    \"password\":\"abtp9!fok\"\n" +
                "}";
    }


}
