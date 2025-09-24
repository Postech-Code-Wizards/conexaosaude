package br.com.conexao.saude.infrastructure.rest;

import br.com.conexao.saude.application.facade.UserFacade;
import br.com.conexao.saude.infrastructure.rest.dto.request.UserRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.UserResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.instancio.Select.field;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserFacade userFacade;

    @TestConfiguration
    static class TestConfig {

        @Bean
        public UserFacade userFacade() {
            return mock(UserFacade.class);
        }

    }

    @Test
    @DisplayName("should register a user and return created status")
    void register_GivenValidUserRequest_ShouldReturnCreatedAndUserResponse() throws Exception {
        UserRequest userRequest = Instancio.of(UserRequest.class)
                .set(field(UserRequest::cpf), "12345678900")
                .set(field(UserRequest::whatsappNumber), "88888888888")
                .create();
        UserResponse userResponse = Instancio.create(UserResponse.class);

        when(userFacade.register(any(UserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(post("/api/v1/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());

        verify(userFacade).register(any(UserRequest.class));
    }

    @Test
    @DisplayName("should find a user by cpf and return ok status")
    void findByUser_GivenValidCpf_ShouldReturnOkAndUserResponse() throws Exception {
        String cpf = "12345678900";
        UserResponse userResponse = Instancio.create(UserResponse.class);

        when(userFacade.findByCpf(cpf)).thenReturn(userResponse);

        mockMvc.perform(get("/api/v1/user")
                        .param("cpf", cpf))
                .andExpect(status().isOk());

        verify(userFacade).findByCpf(cpf);
    }
}