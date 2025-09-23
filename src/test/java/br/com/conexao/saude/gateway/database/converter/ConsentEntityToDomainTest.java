package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsentEntityToDomain Tests")
class ConsentEntityToDomainTest {

    @InjectMocks
    private ConsentEntityToDomain consentEntityToDomain;

    @Test
    @DisplayName("Should convert ConsentEntity to Consent domain")
    void execute_shouldConvertConsentEntityToConsentDomain() {
        ConsentEntity consentEntity = Instancio.create(ConsentEntity.class);

        Consent consent = consentEntityToDomain.execute(consentEntity);

        assertEquals(consentEntity.getId(), consent.getId());
        assertEquals(consentEntity.getConsentPurpose(), consent.getConsentPurpose());
        assertEquals(consentEntity.getConsentVersion(), consent.getConsentVersion());
        assertEquals(consentEntity.getCreatedAt(), consent.getCreatedAt());
        assertEquals(consentEntity.getUpdatedAt(), consent.getUpdatedAt());
    }
}