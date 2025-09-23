package br.com.conexao.saude.gateway.openfeign;

import br.com.conexao.saude.domain.enums.WhatsAppTemplate;
import br.com.conexao.saude.gateway.openfeign.converter.WhatsAppMessageToRequest;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.WhatsAppMessageRequest;
import org.instancio.Instancio;
import org.instancio.TypeToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class WhatsAppMessageOpenFeignAdapterTest {

    @Mock
    private WhatsAppMessageOpenFeignClient whatsAppMessageOpenFeignClient;
    @Mock
    private WhatsAppMessageToRequest whatsAppMessageToRequest;

    @InjectMocks
    private WhatsAppMessageOpenFeignAdapter whatsAppMessageOpenFeignAdapter;

    @Test
    @DisplayName("should send a whatsapp message by delegating to converter and client")
    void send_GivenMessageDetails_ShouldConvertAndSend() {
        String to = Instancio.create(String.class);
        WhatsAppTemplate template = Instancio.create(WhatsAppTemplate.class);
        List<Map<String, String>> headerParameters = Instancio.of(new TypeToken<List<Map<String, String>>>() {}).create();
        List<Map<String, String>> bodyParameters = Instancio.of(new TypeToken<List<Map<String, String>>>() {}).create();
        WhatsAppMessageRequest mockRequest = Instancio.create(WhatsAppMessageRequest.class);

        when(whatsAppMessageToRequest.convert(to, template, headerParameters, bodyParameters)).thenReturn(mockRequest);

        whatsAppMessageOpenFeignAdapter.send(to, template, headerParameters, bodyParameters);

        verify(whatsAppMessageToRequest).convert(to, template, headerParameters, bodyParameters);
        verify(whatsAppMessageOpenFeignClient).execute(mockRequest);
    }
}