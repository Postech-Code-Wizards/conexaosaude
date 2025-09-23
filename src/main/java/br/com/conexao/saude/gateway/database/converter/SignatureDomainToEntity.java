package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignatureDomainToEntity {

    private final ConsentDomainToEntity consentDomainToEntity;
    private final UserDomainToEntity userDomainToEntity;

    public SignatureEntity execute(Signature signature){
        return new SignatureEntity(
                signature.getId(),
                userDomainToEntity.execute(signature.getUser()),
                consentDomainToEntity.execute(signature.getConsent()),
                signature.getInterestContent(),
                signature.getNotificationFrequency(),
                signature.getSignatureDataType(),
                signature.isConsentStatus(),
                signature.getSignatureStatus(),
                signature.getGrantedAt(),
                signature.getUpdatedAt()
        );
    }

}