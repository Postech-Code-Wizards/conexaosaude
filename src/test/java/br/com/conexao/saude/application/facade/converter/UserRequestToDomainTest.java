package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.infrastructure.rest.dto.request.UserRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserRequestToDomain Converter Tests")
class UserRequestToDomainTest {

    @InjectMocks
    private UserRequestToDomain userRequestToDomain;

    @Test
    @DisplayName("Should convert UserRequest to User domain")
    void execute_convertsUserRequestToUser() {
        UserRequest userRequest = Instancio.create(UserRequest.class);

        User user = userRequestToDomain.execute(userRequest);

        assertThat(user.getFullName()).isEqualTo(userRequest.fullName());
        assertThat(user.getCpf()).isEqualTo(userRequest.cpf());
        assertThat(user.getWhatsappNumber()).isEqualTo(userRequest.whatsappNumber());
    }
}