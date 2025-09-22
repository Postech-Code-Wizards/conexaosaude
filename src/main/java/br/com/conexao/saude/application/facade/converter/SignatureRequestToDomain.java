package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.SignatureStatus;
import br.com.conexao.saude.infrastructure.rest.dto.request.SignatureRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SignatureRequestToDomain {

    public Signature execute(SignatureRequest signatureRequest) {

        User user = new User(
                signatureRequest.userId(),
                null,
                null,
                null,
                null,
                null
        );

        Consent consent = new Consent(
                signatureRequest.consentId(),
                null,
                null,
                null,
                null
        );

        return new Signature(
                null,
                user,
                consent,
                signatureRequest.interestContent(),
                signatureRequest.notificationFrequency(),
                signatureRequest.signatureDataType(),
                true,
                SignatureStatus.ACTIVE,
                null,
                null
        );
    }
}
