package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.WhatsAppTemplate;
import br.com.conexao.saude.gateway.NotificationGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DeliverySignatureNotificationUseCase {

    private final NotificationGateway notificationGateway;

    public void execute(User user, String message) {

        final List<Map<String, String>> header = List.of(
                Map.of("type", "text", "text", user.getFullName())
        );

        final List<Map<String, String>> body = List.of(
                Map.of("type", "text", "text", message)
        );

        notificationGateway.send(
                user.getWhatsappNumber(),
                WhatsAppTemplate.SIGNATURE_CONEXAO_SAUDE,
                header,
                body);
    }

}