package br.com.conexao.saude.gateway.database.entities;

import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("SignatureEntity Tests")
class SignatureEntityTest {

    @Test
    @DisplayName("Should create a valid SignatureEntity using Instancio")
    void shouldCreateValidSignatureEntity() {
        SignatureEntity signatureEntity = Instancio.create(SignatureEntity.class);
        assertNotNull(signatureEntity);
    }
}