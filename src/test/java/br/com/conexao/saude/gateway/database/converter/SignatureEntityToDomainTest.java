package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("SignatureEntityToDomain Tests")
class SignatureEntityToDomainTest {

    @InjectMocks
    private SignatureEntityToDomain signatureEntityToDomain;

    @Mock
    private ConsentEntityToDomain consentEntityToDomain;

    @Mock
    private UserEntityToDomain userEntityToDomain;

    @Test
    @DisplayName("Should convert SignatureEntity to Signature domain")
    void execute_shouldConvertSignatureEntityToSignatureDomain() {
        SignatureEntity signatureEntity = Instancio.create(SignatureEntity.class);

        when(userEntityToDomain.execute(signatureEntity.getUserEntity())).thenReturn(Instancio.create(User.class));
        when(consentEntityToDomain.execute(signatureEntity.getConsentEntity())).thenReturn(Instancio.create(Consent.class));

        Signature signature = signatureEntityToDomain.execute(signatureEntity);

        assertEquals(signatureEntity.getId(), signature.getId());
        assertEquals(signatureEntity.getInterestContent(), signature.getInterestContent());
        assertEquals(signatureEntity.getNotificationFrequency(), signature.getNotificationFrequency());
        assertEquals(signatureEntity.getSignatureDataType(), signature.getSignatureDataType());
        assertEquals(signatureEntity.isConsentStatus(), signature.isConsentStatus());
        assertEquals(signatureEntity.getSignatureStatus(), signature.getSignatureStatus());
        assertEquals(signatureEntity.getGrantedAt(), signature.getGrantedAt());
        assertEquals(signatureEntity.getUpdatedAt(), signature.getUpdatedAt());
    }
}