package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.ConsentGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("ConsentUseCase Tests")
class ConsentUseCaseTest {

    @InjectMocks
    private ConsentUseCase consentUseCase;

    @Mock
    private ConsentGateway consentGateway;

    @Test
    @DisplayName("Should find the last consent")
    void findLastConsent_shouldReturnLastConsent() {
        Consent expectedConsent = Instancio.create(Consent.class);
        when(consentGateway.findLastConsent()).thenReturn(expectedConsent);

        Consent actualConsent = consentUseCase.findLastConsent();

        assertThat(actualConsent).isEqualTo(expectedConsent);
        verify(consentGateway, times(1)).findLastConsent();
    }

    @Test
    @DisplayName("Should create a new consent")
    void create_shouldReturnCreatedConsent() {
        Consent consentToCreate = Instancio.create(Consent.class);
        when(consentGateway.create(consentToCreate)).thenReturn(consentToCreate);

        Consent createdConsent = consentUseCase.create(consentToCreate);

        assertThat(createdConsent).isEqualTo(consentToCreate);
        verify(consentGateway, times(1)).create(consentToCreate);
    }

    @Test
    @DisplayName("Should delete a consent by id")
    void delete_shouldDeleteConsentById() {
        Long consentId = Instancio.create(Long.class);

        consentUseCase.delete(consentId);

        verify(consentGateway, times(1)).delete(consentId);
    }
}