package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignatureEntityToDomain {

    private final ConsentEntityToDomain consentEntityToDomain;
    private final UserEntityToDomain userEntityToDomain;

    public Signature execute(SignatureEntity signatureEntity){
        return new Signature(
                signatureEntity.getId(),
                userEntityToDomain.execute(signatureEntity.getUserEntity()),
                consentEntityToDomain.execute(signatureEntity.getConsentEntity()),
                signatureEntity.getInterestContent(),
                signatureEntity.getNotificationFrequency(),
                signatureEntity.getSignatureDataType(),
                signatureEntity.isConsentStatus(),
                signatureEntity.getSignatureStatus(),
                signatureEntity.getGrantedAt(),
                signatureEntity.getUpdatedAt()
        );
    }

}