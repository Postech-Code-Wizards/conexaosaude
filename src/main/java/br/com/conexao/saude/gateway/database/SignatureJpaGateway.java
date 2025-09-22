package br.com.conexao.saude.gateway.database;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.gateway.SignatureGateway;
import br.com.conexao.saude.gateway.database.converter.SignatureDomainToEntity;
import br.com.conexao.saude.gateway.database.converter.SignatureEntityToDomain;
import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import br.com.conexao.saude.gateway.database.repositories.SignatureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignatureJpaGateway implements SignatureGateway {

    private final SignatureRepository signatureRepository;
    private final SignatureDomainToEntity signatureDomainToEntity;
    private final SignatureEntityToDomain signatureEntityToDomain;

    public Signature save(Signature signature) {

        SignatureEntity signatureEntity = signatureDomainToEntity.execute(signature);
        SignatureEntity signatureEntitySaved = signatureRepository.save(signatureEntity);
        return signatureEntityToDomain.execute(signatureEntitySaved);
    }

}