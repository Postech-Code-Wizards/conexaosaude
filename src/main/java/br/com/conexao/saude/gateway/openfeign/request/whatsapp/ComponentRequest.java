package br.com.conexao.saude.gateway.openfeign.request.whatsapp;

import java.util.List;

public record ComponentRequest(String type, List<ParameterRequest> parameters) {}