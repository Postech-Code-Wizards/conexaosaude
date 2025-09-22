package br.com.conexao.saude.gateway.openfeign.request.whatsapp;

import java.util.List;

public record TemplateRequest(String name, LanguageRequest language, List<ComponentRequest> components) {}