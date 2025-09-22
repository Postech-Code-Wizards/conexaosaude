package br.com.conexao.saude.infrastructure.config;

import com.google.api.client.util.Value;
import com.google.genai.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeminiConfiguration {
    @Value("${gemini.api.key}")
    private String apiKey;

    @Bean
    public Client generativeModel() {
        return new Client.Builder()
                .apiKey(apiKey)
                .build();
    }
}
