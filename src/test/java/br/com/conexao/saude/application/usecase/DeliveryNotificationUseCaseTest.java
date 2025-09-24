package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.gateway.UserGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("DeliveryNotificationUseCase Tests")
class DeliveryNotificationUseCaseTest {

    @InjectMocks
    private DeliveryNotificationUseCase deliveryNotificationUseCase;

    @Mock
    private ContentComposerUseCase contentComposerUseCase;

    @Mock
    private DeliverySignatureNotificationUseCase deliverySignatureNotificationUseCase;

    @Mock
    private UserGateway userGateway;

    @Test
    @DisplayName("Should not send notifications when no users are found")
    void execute_noUsersFound_shouldNotSendNotifications() {
        NotificationFrequency notificationFrequency = Instancio.create(NotificationFrequency.class);
        when(userGateway.findAllByNotificationFrequencyOfSignature(notificationFrequency)).thenReturn(Collections.emptyList());

        deliveryNotificationUseCase.execute(notificationFrequency);

        verify(userGateway, times(1)).findAllByNotificationFrequencyOfSignature(notificationFrequency);
        verifyNoInteractions(contentComposerUseCase, deliverySignatureNotificationUseCase);
    }

    @Test
    @DisplayName("Should send notifications to users with the specified notification frequency")
    void execute_usersFound_shouldSendNotifications() {
        NotificationFrequency notificationFrequency = Instancio.create(NotificationFrequency.class);
        List<User> users = Instancio.ofList(User.class).size(2).create();
        String contentMessage = "Test message";

        when(userGateway.findAllByNotificationFrequencyOfSignature(notificationFrequency)).thenReturn(users);
        when(contentComposerUseCase.execute(any(User.class))).thenReturn(contentMessage);

        deliveryNotificationUseCase.execute(notificationFrequency);

        verify(userGateway, times(1)).findAllByNotificationFrequencyOfSignature(notificationFrequency);
        verify(contentComposerUseCase, times(2)).execute(any(User.class));
        verify(deliverySignatureNotificationUseCase, times(2)).execute(any(User.class), eq(contentMessage));
    }
}