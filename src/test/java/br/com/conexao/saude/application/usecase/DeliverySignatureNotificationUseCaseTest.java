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

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeliverySignatureNotificationUseCase Tests")
class DeliverySignatureNotificationUseCaseTest {

    @InjectMocks
    private DeliverySignatureNotificationUseCase deliverySignatureNotificationUseCase;

    @Mock
    private NotificationGateway notificationGateway;

    @Test
    @DisplayName("Should send notification with correct parameters")
    void execute_sendsNotificationWithCorrectParameters() {
        User user = Instancio.create(User.class);
        String message = "Test message";

        deliverySignatureNotificationUseCase.execute(user, message);

        List<Map<String, String>> expectedHeader = List.of(
                Map.of("type", "text", "text", user.getFullName())
        );

        List<Map<String, String>> expectedBody = List.of(
                Map.of("type", "text", "text", message)
        );

        verify(notificationGateway).send(user.getWhatsappNumber(), WhatsAppTemplate.SIGNATURE_CONEXAO_SAUDE, expectedHeader, expectedBody);
    }
}