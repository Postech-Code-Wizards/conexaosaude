package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.facade.converter.UserDomainToResponse;
import br.com.conexao.saude.application.facade.converter.UserRequestToDomain;
import br.com.conexao.saude.application.usecase.UserUseCase;
import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.infrastructure.rest.dto.request.UserRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.UserResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserFacade tests")
class UserFacadeTest {

    @Mock
    private UserUseCase userUseCase;

    @Mock
    private UserRequestToDomain userRequestToDomain;

    @Mock
    private UserDomainToResponse userDomainToResponse;

    @InjectMocks
    private UserFacade userFacade;

    @Test
    @DisplayName("register should convert request, save user and return mapped response")
    void register_convertsSavesAndReturnsMappedResponse() {
        UserRequest request = Instancio.create(UserRequest.class);
        User domain = Instancio.create(User.class);
        User savedDomain = Instancio.create(User.class);
        UserResponse expectedResponse = Instancio.create(UserResponse.class);

        when(userRequestToDomain.execute(request)).thenReturn(domain);
        when(userUseCase.register(domain)).thenReturn(savedDomain);
        when(userDomainToResponse.execute(savedDomain)).thenReturn(expectedResponse);

        UserResponse result = userFacade.register(request);

        assertThat(result).isSameAs(expectedResponse);
        verify(userRequestToDomain).execute(request);
        verify(userUseCase).register(domain);
        verify(userDomainToResponse).execute(savedDomain);
        verifyNoMoreInteractions(userUseCase, userRequestToDomain, userDomainToResponse);
    }

    @Test
    @DisplayName("findByCpf should return mapped user response")
    void findByCpf_returnsMappedResponse() {
        String cpf = Instancio.create(String.class);
        User domain = Instancio.create(User.class);
        UserResponse expectedResponse = Instancio.create(UserResponse.class);

        when(userUseCase.findByCpf(cpf)).thenReturn(domain);
        when(userDomainToResponse.execute(domain)).thenReturn(expectedResponse);

        UserResponse result = userFacade.findByCpf(cpf);

        assertThat(result).isSameAs(expectedResponse);
        verify(userUseCase).findByCpf(cpf);
        verify(userDomainToResponse).execute(domain);
        verifyNoMoreInteractions(userUseCase, userRequestToDomain, userDomainToResponse);
    }
}