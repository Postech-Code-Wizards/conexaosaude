package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.ConsentGateway;
import br.com.conexao.saude.gateway.SignatureGateway;
import br.com.conexao.saude.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignatureUseCase {

    private final WelcomeUserNotificationUseCase welcomeUserNotificationUseCase;
    private final SignatureGateway signatureGateway;
    private final UserGateway userGateway;
    private final ConsentGateway consentGateway;

    public void sign(Signature signature){

        User user = userGateway.findById(signature.getUser().getId());
        consentGateway.findById(signature.getConsent().getId());
        signatureGateway.save(signature);
        welcomeUserNotificationUseCase.send(user);

    }

}