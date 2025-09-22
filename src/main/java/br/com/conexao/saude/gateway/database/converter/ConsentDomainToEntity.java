package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsentDomainToEntity {

    public ConsentEntity execute(Consent consent){
        return new ConsentEntity(
                consent.getId(),
                consent.getConsentPurpose(),
                consent.getConsentVersion(),
                consent.getCreatedAt(),
                consent.getUpdatedAt());

    }
}
