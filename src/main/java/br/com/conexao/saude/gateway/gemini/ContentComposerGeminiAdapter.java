package br.com.conexao.saude.gateway.gemini;

import br.com.conexao.saude.gateway.ContentComposerGateway;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentComposerGeminiAdapter implements ContentComposerGateway {

    private final Client client;
    private static final String AI_MODEL = "gemini-2.5-flash";

    public String generateMessage(String prompt) {
        GenerateContentResponse response = client.models.generateContent(AI_MODEL, prompt, null);
        return response.text();
    }

}