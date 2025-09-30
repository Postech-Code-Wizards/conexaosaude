package br.com.conexao.saude.gateway.sus;

import br.com.conexao.saude.domain.UserSUS;
import br.com.conexao.saude.gateway.sus.convert.UserSUSResponseToDomain;
import br.com.conexao.saude.gateway.sus.response.UserSUSResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserInMemoryDataAdapterTest {

    @Mock
    private UserSUSResponseToDomain userSUSResponseToDomain;

    @InjectMocks
    private UserInMemoryDataAdapter userInMemoryDataAdapter;

    private static final String VALID_CPF_JOAO = "11122233344";
    private static final String INVALID_CPF = "99999999999";

    @Test
    @DisplayName("Should successfully find and convert a UserSUS by a valid CPF")
    void shouldSuccessfullyFindAndConvertByValidCpf() {
        UserSUS expectedUser = Instancio.create(UserSUS.class);

        when(userSUSResponseToDomain.execute(any(UserSUSResponse.class))).thenReturn(expectedUser);

        Optional<UserSUS> result = userInMemoryDataAdapter.findByCpf(VALID_CPF_JOAO);

        assertTrue(result.isPresent());
        assertEquals(expectedUser, result.get());
        verify(userSUSResponseToDomain, times(1)).execute(any(UserSUSResponse.class));
    }

    @Test
    @DisplayName("Should return an empty Optional when the CPF is not found in the map")
    void shouldReturnEmptyOptionalWhenCpfIsNotFound() {
        Optional<UserSUS> result = userInMemoryDataAdapter.findByCpf(INVALID_CPF);

        assertTrue(result.isEmpty());
        verify(userSUSResponseToDomain, never()).execute(any(UserSUSResponse.class));
    }

    @Test
    @DisplayName("Should return an empty Optional when the input CPF is null")
    void shouldReturnEmptyOptionalWhenCpfIsNull() {
        Optional<UserSUS> result = userInMemoryDataAdapter.findByCpf(null);

        assertTrue(result.isEmpty());
        verify(userSUSResponseToDomain, never()).execute(any(UserSUSResponse.class));
    }
}