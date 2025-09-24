package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
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
@DisplayName("SignatureDomainToEntity Tests")
class SignatureDomainToEntityTest {

    @InjectMocks
    private SignatureDomainToEntity signatureDomainToEntity;

    @Mock
    private ConsentDomainToEntity consentDomainToEntity;

    @Mock
    private UserDomainToEntity userDomainToEntity;

    @Test
    @DisplayName("Should convert Signature domain to SignatureEntity")
    void execute_shouldConvertSignatureDomainToSignatureEntity() {
        Signature signature = Instancio.create(Signature.class);

        when(consentDomainToEntity.execute(signature.getConsent())).thenReturn(Instancio.create(ConsentEntity.class));
        when(userDomainToEntity.execute(signature.getUser())).thenReturn(Instancio.create(UserEntity.class));

        SignatureEntity signatureEntity = signatureDomainToEntity.execute(signature);

        assertEquals(signature.getId(), signatureEntity.getId());
        assertEquals(signature.getInterestContent(), signatureEntity.getInterestContent());
        assertEquals(signature.getNotificationFrequency(), signatureEntity.getNotificationFrequency());
        assertEquals(signature.getSignatureDataType(), signatureEntity.getSignatureDataType());
        assertEquals(signature.isConsentStatus(), signatureEntity.isConsentStatus());
        assertEquals(signature.getSignatureStatus(), signatureEntity.getSignatureStatus());
        assertEquals(signature.getGrantedAt(), signatureEntity.getGrantedAt());
        assertEquals(signature.getUpdatedAt(), signatureEntity.getUpdatedAt());
    }
}