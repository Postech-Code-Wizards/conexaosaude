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
public class WelcomeUserNotificationUseCase {

    private final NotificationGateway notificationGateway;

    public void send(User user) {

        final List<Map<String, String>> header = List.of(
                Map.of("type", "text", "text", user.getFullName())
        );
        final List<Map<String, String>> body = List.of();
        notificationGateway.send(
                user.getWhatsappNumber(),
                WhatsAppTemplate.WELCOME_CONEXAO_SAUDE_V2,
                header,
                body);
    }

}