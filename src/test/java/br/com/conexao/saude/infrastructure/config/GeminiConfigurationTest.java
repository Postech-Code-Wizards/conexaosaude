package br.com.conexao.saude.infrastructure.config;

import com.google.genai.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GeminiConfigurationTest {

    private GeminiConfiguration geminiConfiguration;
    private static final String MOCK_API_KEY = "test-api-key-xyz123";

    @BeforeEach
    void setUp() {
        geminiConfiguration = new GeminiConfiguration();

        ReflectionTestUtils.setField(geminiConfiguration, "apiKey", MOCK_API_KEY);
    }

    @Test
    @DisplayName("Should correctly initialize the Gemini Client bean")
    void generativeModel_ShouldReturnClientInstance() {
        Client client = geminiConfiguration.generativeModel();

        assertNotNull(client, "The generated Client object should not be null.");

        assertTrue(client instanceof Client, "The returned object must be an instance of com.google.genai.Client.");

    }
}
