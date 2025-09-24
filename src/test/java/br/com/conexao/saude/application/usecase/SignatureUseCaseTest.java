package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.Consent;
import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.ConsentGateway;
import br.com.conexao.saude.gateway.SignatureGateway;
import br.com.conexao.saude.gateway.UserGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("SignatureUseCase Tests")
class SignatureUseCaseTest {

    @InjectMocks
    private SignatureUseCase signatureUseCase;

    @Mock
    private WelcomeUserNotificationUseCase welcomeUserNotificationUseCase;

    @Mock
    private SignatureGateway signatureGateway;

    @Mock
    private UserGateway userGateway;

    @Mock
    private ConsentGateway consentGateway;

    @Test
    @DisplayName("Should sign a signature successfully")
    void sign_shouldSignSuccessfully() {
        Signature signature = Instancio.create(Signature.class);
        User user = Instancio.create(User.class);
        Consent consent = Instancio.create(Consent.class);

        when(userGateway.findById(signature.getUser().getId())).thenReturn(user);
        when(consentGateway.findById(signature.getConsent().getId())).thenReturn(consent);

        signatureUseCase.sign(signature);

        verify(userGateway, times(1)).findById(signature.getUser().getId());
        verify(consentGateway, times(1)).findById(signature.getConsent().getId());
        verify(signatureGateway, times(1)).save(signature);
        verify(welcomeUserNotificationUseCase, times(1)).send(user);
    }
}