package br.com.conexao.saude.gateway.database.entities;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("ConsentEntity Tests")
class ConsentEntityTest {

    @Test
    @DisplayName("Should create a valid ConsentEntity using Instancio")
    void shouldCreateValidConsentEntity() {
        ConsentEntity consentEntity = Instancio.create(ConsentEntity.class);
        assertNotNull(consentEntity);
    }
}