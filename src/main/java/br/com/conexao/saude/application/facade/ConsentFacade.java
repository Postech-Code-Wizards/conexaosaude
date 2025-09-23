package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.facade.converter.ConsentDomainToResponse;
import br.com.conexao.saude.application.facade.converter.ConsentRequestToDomain;
import br.com.conexao.saude.application.usecase.ConsentUseCase;
import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.infrastructure.rest.dto.request.ConsentRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.ConsentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsentFacade {

    private final ConsentUseCase consentUseCase;
    private final ConsentDomainToResponse consentDomainToResponse;
    private final ConsentRequestToDomain consentRequestToDomain;

    public ConsentResponse findLastConsent() {
        Consent consent = consentUseCase.findLastConsent();
        return consentDomainToResponse.execute(consent);
    }

    public ConsentResponse createConsent(ConsentRequest consentRequest) {
        Consent consent = consentRequestToDomain.execute(consentRequest);
        Consent consentSaved = consentUseCase.create(consent);
        return consentDomainToResponse.execute(consentSaved);
    }

    public void deleteConsent(Long id) {
        consentUseCase.delete(id);
    }

}