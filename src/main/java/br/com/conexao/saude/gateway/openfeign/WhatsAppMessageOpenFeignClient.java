package br.com.conexao.saude.gateway.openfeign;

import br.com.conexao.saude.gateway.openfeign.configuration.FeignClientWhatsAppConfig;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.WhatsAppMessageRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "whatsapp-api", url = "${whatsapp.service.url}", configuration = FeignClientWhatsAppConfig.class)
public interface WhatsAppMessageOpenFeignClient {

    @PostMapping(path = "/messages")
    void execute(@RequestBody WhatsAppMessageRequest request);

}