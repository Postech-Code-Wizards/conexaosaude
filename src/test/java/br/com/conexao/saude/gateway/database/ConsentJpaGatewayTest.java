package br.com.conexao.saude.gateway.database;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.database.converter.ConsentDomainToEntity;
import br.com.conexao.saude.gateway.database.converter.ConsentEntityToDomain;
import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import br.com.conexao.saude.gateway.database.repositories.ConsentRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsentJpaGateway Tests")
class ConsentJpaGatewayTest {

    @InjectMocks
    private ConsentJpaGateway consentJpaGateway;

    @Mock
    private ConsentRepository consentRepository;

    @Mock
    private ConsentEntityToDomain consentEntityToDomain;

    @Mock
    private ConsentDomainToEntity consentDomainToEntity;

    @Test
    @DisplayName("Should find the last consent")
    void findLastConsent_shouldReturnLastConsent() {
        ConsentEntity consentEntity = Instancio.create(ConsentEntity.class);
        Consent consent = Instancio.create(Consent.class);

        when(consentRepository.findFirstByOrderByConsentVersionDesc()).thenReturn(Optional.of(consentEntity));
        when(consentEntityToDomain.execute(consentEntity)).thenReturn(consent);

        Consent result = consentJpaGateway.findLastConsent();

        assertNotNull(result);
        assertEquals(consent, result);
        verify(consentRepository, times(1)).findFirstByOrderByConsentVersionDesc();
        verify(consentEntityToDomain, times(1)).execute(consentEntity);
    }

    @Test
    @DisplayName("Should throw exception when last consent is not found")
    void findLastConsent_shouldThrowExceptionWhenConsentNotFound() {
        when(consentRepository.findFirstByOrderByConsentVersionDesc()).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> consentJpaGateway.findLastConsent());
        verify(consentRepository, times(1)).findFirstByOrderByConsentVersionDesc();
        verifyNoInteractions(consentEntityToDomain);
    }

    @Test
    @DisplayName("Should find consent by id")
    void findById_shouldReturnConsent() {
        Long id = Instancio.create(Long.class);
        ConsentEntity consentEntity = Instancio.create(ConsentEntity.class);
        Consent consent = Instancio.create(Consent.class);

        when(consentRepository.findById(id)).thenReturn(Optional.of(consentEntity));
        when(consentEntityToDomain.execute(consentEntity)).thenReturn(consent);

        Consent result = consentJpaGateway.findById(id);

        assertNotNull(result);
        assertEquals(consent, result);
        verify(consentRepository, times(1)).findById(id);
        verify(consentEntityToDomain, times(1)).execute(consentEntity);
    }

    @Test
    @DisplayName("Should throw exception when consent is not found by id")
    void findById_shouldThrowExceptionWhenConsentNotFound() {
        Long id = Instancio.create(Long.class);
        when(consentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> consentJpaGateway.findById(id));
        verify(consentRepository, times(1)).findById(id);
        verifyNoInteractions(consentEntityToDomain);
    }

    @Test
    @DisplayName("Should create a consent")
    void create_shouldCreateConsent() {
        Consent consent = Instancio.create(Consent.class);
        ConsentEntity consentEntity = Instancio.create(ConsentEntity.class);
        ConsentEntity savedConsentEntity = Instancio.create(ConsentEntity.class);
        Consent savedConsent = Instancio.create(Consent.class);

        when(consentDomainToEntity.execute(consent)).thenReturn(consentEntity);
        when(consentRepository.save(consentEntity)).thenReturn(savedConsentEntity);
        when(consentEntityToDomain.execute(savedConsentEntity)).thenReturn(savedConsent);

        Consent result = consentJpaGateway.create(consent);

        assertNotNull(result);
        assertEquals(savedConsent, result);
        verify(consentDomainToEntity, times(1)).execute(consent);
        verify(consentRepository, times(1)).save(consentEntity);
        verify(consentEntityToDomain, times(1)).execute(savedConsentEntity);
    }

    @Test
    @DisplayName("Should delete consent by id")
    void delete_shouldDeleteConsent() {
        Long id = Instancio.create(Long.class);

        consentJpaGateway.delete(id);

        verify(consentRepository, times(1)).deleteById(id);
    }
}