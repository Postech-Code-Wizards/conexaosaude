package br.com.conexao.saude.gateway;

import br.com.conexao.saude.domain.enums.WhatsAppTemplate;

import java.util.List;
import java.util.Map;

public interface NotificationGateway {

    void send(String to, WhatsAppTemplate template, List<Map<String, String>> headerParameters, List<Map<String, String>> bodyParameters);

}