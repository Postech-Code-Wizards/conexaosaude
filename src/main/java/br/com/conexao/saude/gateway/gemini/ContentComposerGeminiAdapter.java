package br.com.conexao.saude.gateway.gemini;

import br.com.conexao.saude.gateway.ContentComposerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ContentComposerGeminiAdapter implements ContentComposerGateway {

    @Override
    public String generate(String recipientName) {

        // todo: implementar chamada para o Gemini API
        return UUID.randomUUID().toString();

    }

}