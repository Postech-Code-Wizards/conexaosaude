package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.usecase.DeliveryNotificationUseCase;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@ExtendWith(MockitoExtension.class)
class DeliveryNotificationFacadeTest {

    @Mock
    private DeliveryNotificationUseCase deliveryNotificationUseCase;

    @InjectMocks
    private DeliveryNotificationFacade deliveryNotificationFacade;

    @Test
    @DisplayName("deliveryNotification should delegate execution to use case")
    void deliveryNotification_delegatesExecutionToUseCase() {
        NotificationFrequency frequency = Instancio.create(NotificationFrequency.class);

        deliveryNotificationFacade.deliveryNotification(frequency);

        verify(deliveryNotificationUseCase).execute(frequency);
        verifyNoMoreInteractions(deliveryNotificationUseCase);
    }
}