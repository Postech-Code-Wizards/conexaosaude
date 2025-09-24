package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.infrastructure.rest.dto.request.ConsentRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsentRequestToDomainTest")
class ConsentRequestToDomainTest {

    @InjectMocks
    private ConsentRequestToDomain consentRequestToDomain;

    @Test
    @DisplayName("Should convert ConsentRequest to Consent domain")
    void execute_convertsConsentRequestToConsent() {
        ConsentRequest consentRequest = Instancio.create(ConsentRequest.class);

        Consent consent = consentRequestToDomain.execute(consentRequest);

        assertThat(consent.getConsentPurpose()).isEqualTo(consentRequest.consentPurpose());
        assertThat(consent.getConsentVersion()).isEqualTo(consentRequest.consentVersion());
    }
}