package br.com.conexao.saude.gateway.database.entities;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("UserEntity Tests")
class UserEntityTest {

    @Test
    @DisplayName("Should create a valid UserEntity using Instancio")
    void shouldCreateValidUserEntity() {
        UserEntity userEntity = Instancio.create(UserEntity.class);
        assertNotNull(userEntity);
    }
}