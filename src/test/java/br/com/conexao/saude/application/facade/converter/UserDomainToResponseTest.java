package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.infrastructure.rest.dto.response.UserResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserDomainToResponse Converter Tests")
class UserDomainToResponseTest {

    @InjectMocks
    private UserDomainToResponse userDomainToResponse;

    @Test
    @DisplayName("Should convert User domain to UserResponse")
    void execute_convertsUserToUserResponse() {
        User user = Instancio.create(User.class);

        UserResponse response = userDomainToResponse.execute(user);

        assertThat(response.id()).isEqualTo(user.getId());
        assertThat(response.fullName()).isEqualTo(user.getFullName());
        assertThat(response.cpf()).isEqualTo(user.getCpf());
        assertThat(response.whatsappNumber()).isEqualTo(user.getWhatsappNumber());
        assertThat(response.createdAt()).isEqualTo(user.getCreatedAt());
        assertThat(response.updatedAt()).isEqualTo(user.getUpdatedAt());
    }
}