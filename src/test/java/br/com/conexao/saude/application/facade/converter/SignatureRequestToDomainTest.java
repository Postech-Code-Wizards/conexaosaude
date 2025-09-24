package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.infrastructure.rest.dto.request.SignatureRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
@DisplayName("SignatureRequestToDomain Converter Tests")
class SignatureRequestToDomainTest {

    @InjectMocks
    private SignatureRequestToDomain signatureRequestToDomain;

    @Test
    @DisplayName("Should convert SignatureRequest to Signature domain")
    void execute_convertsSignatureRequestToSignature() {
        SignatureRequest signatureRequest = Instancio.create(SignatureRequest.class);

        Signature signature = signatureRequestToDomain.execute(signatureRequest);

        assertThat(signature.getInterestContent()).isEqualTo(signatureRequest.interestContent());
        assertThat(signature.getNotificationFrequency()).isEqualTo(signatureRequest.notificationFrequency());
        assertThat(signature.getSignatureDataType()).isEqualTo(signatureRequest.signatureDataType());

    }
}