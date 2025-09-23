package br.com.conexao.saude.gateway.openfeign.request.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WhatsAppMessageRequest(
        @JsonProperty("messaging_product") String messagingProduct,
        String to,
        String type,
        TemplateRequest template
) {}