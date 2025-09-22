package br.com.conexao.saude.gateway.openfeign;

import br.com.conexao.saude.domain.enums.WhatsAppTemplate;
import br.com.conexao.saude.gateway.NotificationGateway;
import br.com.conexao.saude.gateway.openfeign.converter.WhatsAppMessageToRequest;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.WhatsAppMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class WhatsAppMessageOpenFeignAdapter implements NotificationGateway {

    private final WhatsAppMessageOpenFeignClient whatsAppMessageOpenFeignClient;
    private final WhatsAppMessageToRequest whatsAppMessageToRequest;

    @Override
    public void send(String to, WhatsAppTemplate template, List<Map<String, String>> headerParameters, List<Map<String, String>> bodyParameters) {

        WhatsAppMessageRequest whatsAppMessageRequest = whatsAppMessageToRequest.convert(to, template, headerParameters, bodyParameters);
        whatsAppMessageOpenFeignClient.execute(whatsAppMessageRequest);

    }

}