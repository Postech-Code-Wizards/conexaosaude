package br.com.conexao.saude.gateway;

import br.com.conexao.saude.domain.Consent;

public interface ConsentGateway {

    Consent findLastConsent();

    Consent findById(Long id);

    Consent create(Consent consent);

    void delete(Long id);
}