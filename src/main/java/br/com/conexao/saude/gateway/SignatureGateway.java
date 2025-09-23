package br.com.conexao.saude.gateway;

import br.com.conexao.saude.domain.Signature;

public interface SignatureGateway {

    Signature save(Signature signature);

}