package br.com.conexao.saude.gateway.database;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.ConsentGateway;
import br.com.conexao.saude.gateway.database.converter.ConsentDomainToEntity;
import br.com.conexao.saude.gateway.database.converter.ConsentEntityToDomain;
import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import br.com.conexao.saude.gateway.database.repositories.ConsentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConsentJpaGateway implements ConsentGateway {

    private final ConsentRepository consentRepository;
    private final ConsentEntityToDomain consentEntityToDomain;
    private final ConsentDomainToEntity consentDomainToEntity;

    @Override
    public Consent findLastConsent() {
        ConsentEntity consentEntity =
                consentRepository.findFirstByOrderByConsentVersionDesc()
                        .orElseThrow(() -> new RuntimeException("Consent not found."));
        return consentEntityToDomain.execute(consentEntity);
    }

    @Override
    public Consent findById(Long id) {
        ConsentEntity consentEntity =
                consentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Consent not found."));
        return consentEntityToDomain.execute(consentEntity);
    }

    @Override
    public Consent create(Consent consent) {
        ConsentEntity consentEntity = consentDomainToEntity.execute(consent);
        ConsentEntity consentSaved = consentRepository.save(consentEntity);
        return consentEntityToDomain.execute(consentSaved);
    }

    @Override
    public void delete(Long id) {
        consentRepository.deleteById(id);
    }

}
