package br.com.conexao.saude.gateway.database;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.gateway.database.converter.SignatureDomainToEntity;
import br.com.conexao.saude.gateway.database.converter.SignatureEntityToDomain;
import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import br.com.conexao.saude.gateway.database.repositories.SignatureRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignatureJpaGatewayTest {

    @Mock
    private SignatureRepository signatureRepository;
    @Mock
    private SignatureDomainToEntity signatureDomainToEntity;
    @Mock
    private SignatureEntityToDomain signatureEntityToDomain;

    @InjectMocks
    private SignatureJpaGateway signatureJpaGateway;

    @Test
    @DisplayName("should save a signature")
    void save_GivenSignature_ShouldReturnSavedSignature() {
        Signature signature = Instancio.create(Signature.class);
        SignatureEntity signatureEntity = Instancio.create(SignatureEntity.class);
        SignatureEntity signatureEntitySaved = Instancio.create(SignatureEntity.class);
        Signature signatureSaved = Instancio.create(Signature.class);

        when(signatureDomainToEntity.execute(any(Signature.class))).thenReturn(signatureEntity);
        when(signatureRepository.save(any(SignatureEntity.class))).thenReturn(signatureEntitySaved);
        when(signatureEntityToDomain.execute(any(SignatureEntity.class))).thenReturn(signatureSaved);

        Signature result = signatureJpaGateway.save(signature);

        assertNotNull(result);
        assertEquals(signatureSaved, result);

        verify(signatureDomainToEntity).execute(signature);
        verify(signatureRepository).save(signatureEntity);
        verify(signatureEntityToDomain).execute(signatureEntitySaved);
    }
}