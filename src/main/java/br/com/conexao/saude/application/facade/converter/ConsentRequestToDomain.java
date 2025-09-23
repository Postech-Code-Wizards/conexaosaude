package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.infrastructure.rest.dto.request.ConsentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsentRequestToDomain {

    public Consent execute(ConsentRequest consentRequest) {
        return new Consent(
                null,
                consentRequest.consentPurpose(),
                consentRequest.consentVersion(),
                null,
                null);
    }
}
