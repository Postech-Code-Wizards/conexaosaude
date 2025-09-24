package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.WhatsAppTemplate;
import br.com.conexao.saude.gateway.NotificationGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("WelcomeUserNotificationUseCase Tests")
class WelcomeUserNotificationUseCaseTest {

    @InjectMocks
    private WelcomeUserNotificationUseCase welcomeUserNotificationUseCase;

    @Mock
    private NotificationGateway notificationGateway;

    @Test
    @DisplayName("Should send welcome notification with correct parameters")
    void send_sendsNotificationWithCorrectParameters() {
        User user = Instancio.create(User.class);

        welcomeUserNotificationUseCase.send(user);

        List<Map<String, String>> expectedHeader = List.of(
                Map.of("type", "text", "text", user.getFullName())
        );

        List<Map<String, String>> expectedBody = List.of();

        verify(notificationGateway).send(user.getWhatsappNumber(), WhatsAppTemplate.WELCOME_CONEXAO_SAUDE_V2, expectedHeader, expectedBody);
    }
}