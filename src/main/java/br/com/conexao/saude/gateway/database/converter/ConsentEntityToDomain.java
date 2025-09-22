package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsentEntityToDomain {

    public Consent execute(ConsentEntity consentEntity) {
        return new Consent(
                consentEntity.getId(),
                consentEntity.getConsentPurpose(),
                consentEntity.getConsentVersion(),
                consentEntity.getCreatedAt(),
                consentEntity.getUpdatedAt());
    }
}
