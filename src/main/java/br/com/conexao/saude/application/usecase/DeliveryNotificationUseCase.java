package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryNotificationUseCase {

    private final ContentComposerUseCase contentComposerUseCase;
    private final DeliverySignatureNotificationUseCase deliverySignatureNotificationUseCase;
    private final UserGateway userGateway;

    public void execute(NotificationFrequency notificationFrequency) {

        List<User> listUserByNotificationFrequency = userGateway.findAllByNotificationFrequencyOfSignature(notificationFrequency);
        if(listUserByNotificationFrequency == null || listUserByNotificationFrequency.isEmpty()) {
            return;
        }

        listUserByNotificationFrequency.forEach(user -> {
                    String contentMessage = contentComposerUseCase.execute(user);
                    deliverySignatureNotificationUseCase.execute(user, contentMessage);
                }
        );
    }

}