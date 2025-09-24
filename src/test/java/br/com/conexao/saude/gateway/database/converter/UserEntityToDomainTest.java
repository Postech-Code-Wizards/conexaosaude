package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("UserEntityToDomain Tests")
class UserEntityToDomainTest {

    @InjectMocks
    private UserEntityToDomain userEntityToDomain;

    @Test
    @DisplayName("Should convert UserEntity to User domain")
    void execute_shouldConvertUserEntityToUserDomain() {
        UserEntity userEntity = Instancio.create(UserEntity.class);

        User user = userEntityToDomain.execute(userEntity);

        assertEquals(userEntity.getId(), user.getId());
        assertEquals(userEntity.getFullName(), user.getFullName());
        assertEquals(userEntity.getCpf(), user.getCpf());
        assertEquals(userEntity.getWhatsappNumber(), user.getWhatsappNumber());
        assertEquals(userEntity.getCreatedAt(), user.getCreatedAt());
        assertEquals(userEntity.getUpdatedAt(), user.getUpdatedAt());
    }
}