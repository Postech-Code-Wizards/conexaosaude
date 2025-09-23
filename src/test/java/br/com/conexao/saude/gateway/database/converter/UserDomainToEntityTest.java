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
@DisplayName("UserDomainToEntity Tests")
class UserDomainToEntityTest {

    @InjectMocks
    private UserDomainToEntity userDomainToEntity;

    @Test
    @DisplayName("Should convert User domain to UserEntity")
    void convert_shouldExecuteUserDomainToUserEntity() {
        User user = Instancio.create(User.class);

        UserEntity userEntity = userDomainToEntity.execute(user);

        assertEquals(user.getId(), userEntity.getId());
        assertEquals(user.getFullName(), userEntity.getFullName());
        assertEquals(user.getCpf(), userEntity.getCpf());
        assertEquals(user.getWhatsappNumber(), userEntity.getWhatsappNumber());
        assertEquals(user.getCreatedAt(), userEntity.getCreatedAt());
        assertEquals(user.getUpdatedAt(), userEntity.getUpdatedAt());
    }
}