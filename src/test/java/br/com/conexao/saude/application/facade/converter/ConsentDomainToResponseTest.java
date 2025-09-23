package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.infrastructure.rest.dto.response.ConsentResponse;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ConsentDomainToResponseTest {

    @InjectMocks
    private ConsentDomainToResponse consentDomainToResponse;

    @Test
    @DisplayName("Should convert Consent domain to ConsentResponse")
    void execute_convertsConsentToConsentResponse() {
        Consent consent = Instancio.create(Consent.class);

        ConsentResponse response = consentDomainToResponse.execute(consent);

        assertThat(response.id()).isEqualTo(consent.getId());
        assertThat(response.consentPurpose()).isEqualTo(consent.getConsentPurpose());
        assertThat(response.consentVersion()).isEqualTo(consent.getConsentVersion());
        assertThat(response.createdAt()).isEqualTo(consent.getCreatedAt());
        assertThat(response.updatedAt()).isEqualTo(consent.getUpdatedAt());
    }
}