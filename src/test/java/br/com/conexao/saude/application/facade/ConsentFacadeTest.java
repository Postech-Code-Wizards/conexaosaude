package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.facade.converter.ConsentDomainToResponse;
import br.com.conexao.saude.application.facade.converter.ConsentRequestToDomain;
import br.com.conexao.saude.application.usecase.ConsentUseCase;
import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.infrastructure.rest.dto.request.ConsentRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.ConsentResponse;
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
class ConsentFacadeTest {

    @Mock
    private ConsentUseCase consentUseCase;

    @Mock
    private ConsentDomainToResponse consentDomainToResponse;

    @Mock
    private ConsentRequestToDomain consentRequestToDomain;

    @InjectMocks
    private ConsentFacade consentFacade;

    @Test
    @DisplayName("findLastConsent should return mapped consent response")
    void findLastConsent_returnsMappedResponse() {
        Consent domain = Instancio.create(Consent.class);
        ConsentResponse expectedResponse = Instancio.create(ConsentResponse.class);

        when(consentUseCase.findLastConsent()).thenReturn(domain);
        when(consentDomainToResponse.execute(domain)).thenReturn(expectedResponse);

        ConsentResponse result = consentFacade.findLastConsent();

        assertThat(result).isSameAs(expectedResponse);
        verify(consentUseCase).findLastConsent();
        verify(consentDomainToResponse).execute(domain);
        verifyNoMoreInteractions(consentUseCase, consentDomainToResponse, consentRequestToDomain);
    }

    @Test
    @DisplayName("createConsent should convert request, save and return mapped response")
    void createConsent_convertsSavesAndReturnsResponse() {
        ConsentRequest request = Instancio.create(ConsentRequest.class);
        Consent domain = Instancio.create(Consent.class);
        Consent savedDomain = Instancio.create(Consent.class);
        ConsentResponse expectedResponse = Instancio.create(ConsentResponse.class);

        when(consentRequestToDomain.execute(request)).thenReturn(domain);
        when(consentUseCase.create(domain)).thenReturn(savedDomain);
        when(consentDomainToResponse.execute(savedDomain)).thenReturn(expectedResponse);

        ConsentResponse result = consentFacade.createConsent(request);

        assertThat(result).isSameAs(expectedResponse);
        verify(consentRequestToDomain).execute(request);
        verify(consentUseCase).create(domain);
        verify(consentDomainToResponse).execute(savedDomain);
        verifyNoMoreInteractions(consentUseCase, consentDomainToResponse, consentRequestToDomain);
    }

    @Test
    @DisplayName("deleteConsent should delegate deletion to use case")
    void deleteConsent_delegatesToUseCase() {
        Long id = Instancio.create(Long.class);

        consentFacade.deleteConsent(id);

        verify(consentUseCase).delete(id);
        verifyNoMoreInteractions(consentUseCase, consentDomainToResponse, consentRequestToDomain);
    }
}