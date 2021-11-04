package br.com.itau.selective.process.controller;

import br.com.itau.selective.process.model.PasswordResponse;
import br.com.itau.selective.process.model.UserRequest;
import br.com.itau.selective.process.service.PasswordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static br.com.itau.selective.process.commons.constant.ApplicationConstant.Password.V1.PASSWORD_VALIDATE;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
public class PasswordControllerTest {
    private MockMvc mockMvc;
    private HttpHeaders headers;
    @Mock
    private PasswordService passwordService;
    @InjectMocks
    private PasswordController passwordController;

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(passwordController).build();
        headers = new HttpHeaders();
    }

    @Test
    public void shouldReturnCreatedWhenSendPasswordValid() throws Exception {
        when(passwordService.validate(any(UserRequest.class))).thenReturn(PasswordResponse.builder()
                .passwordValid(true)
                .build());
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


    private String getUserRequestPasswordValid() {
        return "{\n" +
                "    \"password\":\"AbTp9!fok\"\n" +
                "}";
    }
}
