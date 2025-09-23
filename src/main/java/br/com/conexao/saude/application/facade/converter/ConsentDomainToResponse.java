package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.infrastructure.rest.dto.response.ConsentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsentDomainToResponse {

    public ConsentResponse execute(Consent consent) {
        return new ConsentResponse(
                consent.getId(),
                consent.getConsentPurpose(),
                consent.getConsentVersion(),
                consent.getCreatedAt(),
                consent.getUpdatedAt());
    }
}
