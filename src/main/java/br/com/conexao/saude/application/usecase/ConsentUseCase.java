package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.gateway.ConsentGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ConsentUseCase {

    private final ConsentGateway consentGateway;

    public Consent findLastConsent() {
        return consentGateway.findLastConsent();
    }

    public Consent create(Consent consent) {
        return consentGateway.create(consent);
    }

    public void delete(Long id) {
        consentGateway.delete(id);
    }

}
