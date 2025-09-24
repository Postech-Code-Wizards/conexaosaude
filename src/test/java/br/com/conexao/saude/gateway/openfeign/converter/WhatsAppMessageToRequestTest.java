package br.com.conexao.saude.gateway.openfeign.converter;

import br.com.conexao.saude.domain.enums.WhatsAppTemplate;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.WhatsAppMessageRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WhatsAppMessageToRequestTest {

    private final WhatsAppMessageToRequest converter = new WhatsAppMessageToRequest();

    @Test
    @DisplayName("should convert to request with header and body parameters")
    void convert_WithHeaderAndBodyParameters_ShouldReturnWhatsAppMessageRequest() {
        String to = Instancio.create(String.class);
        WhatsAppTemplate template = Instancio.create(WhatsAppTemplate.class);
        List<Map<String, String>> headerParameters = List.of(Map.of("type", "text", "text", Instancio.create(String.class)));
        List<Map<String, String>> bodyParameters = List.of(Map.of("type", "text", "text", Instancio.create(String.class)));

        WhatsAppMessageRequest result = converter.convert(to, template, headerParameters, bodyParameters);

        assertNotNull(result);
    }

    @Test
    @DisplayName("should convert to request with header parameters only")
    void convert_WithHeaderParametersOnly_ShouldReturnWhatsAppMessageRequest() {
        String to = Instancio.create(String.class);
        WhatsAppTemplate template = Instancio.create(WhatsAppTemplate.class);
        List<Map<String, String>> headerParameters = List.of(Map.of("type", "text", "text", Instancio.create(String.class)));

        WhatsAppMessageRequest result = converter.convert(to, template, headerParameters, null);

        assertNotNull(result);
    }
}