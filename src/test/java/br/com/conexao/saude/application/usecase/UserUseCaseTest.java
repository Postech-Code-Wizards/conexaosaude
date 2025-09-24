package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.UserGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserUseCase Tests")
class UserUseCaseTest {

    @InjectMocks
    private UserUseCase userUseCase;

    @Mock
    private UserGateway userGateway;

    @Test
    @DisplayName("Should register a user")
    void register_shouldReturnRegisteredUser() {
        User userToRegister = Instancio.create(User.class);
        when(userGateway.save(userToRegister)).thenReturn(userToRegister);

        User registeredUser = userUseCase.register(userToRegister);

        assertThat(registeredUser).isEqualTo(userToRegister);
        verify(userGateway, times(1)).save(userToRegister);
    }

    @Test
    @DisplayName("Should find a user by CPF")
    void findByCpf_shouldReturnUserByCpf() {
        String cpf = Instancio.create(String.class);
        User expectedUser = Instancio.create(User.class);
        when(userGateway.findByCpf(cpf)).thenReturn(expectedUser);

        User actualUser = userUseCase.findByCpf(cpf);

        assertThat(actualUser).isEqualTo(expectedUser);
        verify(userGateway, times(1)).findByCpf(cpf);
    }
}