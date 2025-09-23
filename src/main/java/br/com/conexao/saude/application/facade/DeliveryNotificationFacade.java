package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.usecase.DeliveryNotificationUseCase;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryNotificationFacade {

    private final DeliveryNotificationUseCase deliveryNotificationUseCase;

    public void deliveryNotification(NotificationFrequency notificationFrequency) {
        deliveryNotificationUseCase.execute(notificationFrequency);
    }

}