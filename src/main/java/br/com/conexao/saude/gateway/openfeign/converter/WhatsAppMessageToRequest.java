package br.com.conexao.saude.gateway.openfeign.converter;

import br.com.conexao.saude.domain.enums.WhatsAppTemplate;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.ComponentRequest;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.LanguageRequest;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.ParameterRequest;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.TemplateRequest;
import br.com.conexao.saude.gateway.openfeign.request.whatsapp.WhatsAppMessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class WhatsAppMessageToRequest {

    private static final String HEADER = "header";
    private static final String BODY = "body";
    private static final String MESSAGING_PRODUCT = "whatsapp";
    private static final String TYPE = "template";
    private static final String LANGUAGE = "pt_BR";

    public WhatsAppMessageRequest convert(String to, WhatsAppTemplate template, List<Map<String, String>> headerParameters, List<Map<String, String>> bodyParameters) {

        List<ParameterRequest> headerParameterRequests = mapToParameterRequest(headerParameters);
        List<ParameterRequest> bodyParameterRequests = mapToParameterRequest(bodyParameters);
        List<ComponentRequest> componentRequests = new ArrayList<>();
        componentRequests.add(new ComponentRequest(HEADER, headerParameterRequests));

        if (!bodyParameterRequests.isEmpty()) {
            componentRequests.add(new ComponentRequest(BODY, bodyParameterRequests));
        }

        return new WhatsAppMessageRequest(
                MESSAGING_PRODUCT,
                to,
                TYPE,
                new TemplateRequest(
                        template.toString().toLowerCase(),
                        new LanguageRequest(LANGUAGE),
                        componentRequests
                )
        );
    }

    private List<ParameterRequest> mapToParameterRequest(List<Map<String, String>> parameters) {
        if (parameters == null) {
            return Collections.emptyList();
        }
        return parameters.stream()
                .map(paramMap -> new ParameterRequest(paramMap.get("type"), paramMap.get("text")))
                .toList();
    }

}