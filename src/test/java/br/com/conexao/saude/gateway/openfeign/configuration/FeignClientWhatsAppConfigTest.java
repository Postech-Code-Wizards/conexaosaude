package br.com.conexao.saude.gateway.openfeign.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FeignClientWhatsAppConfigTest {

    private FeignClientWhatsAppConfig config;
    private static final String MOCK_TOKEN = "test-whatsapp-token-12345";
    private static final String AUTH_HEADER = "Authorization";
    private static final String EXPECTED_VALUE = "Bearer " + MOCK_TOKEN;

    @BeforeEach
    void setUp() {
        config = new FeignClientWhatsAppConfig();
        // Simula a injeção do valor do token que viria do @Value
        ReflectionTestUtils.setField(config, "token", MOCK_TOKEN);
    }

    @Test
    @DisplayName("Should create RequestInterceptor that correctly adds Bearer token header")
    void requestInterceptor_ShouldAddAuthorizationHeader() {
        RequestInterceptor interceptor = config.requestInterceptor();
        RequestTemplate template = new RequestTemplate();

        interceptor.apply(template);

        assertTrue(template.headers().containsKey(AUTH_HEADER),
                "The RequestTemplate must contain the Authorization header.");

        assertEquals(EXPECTED_VALUE, template.headers().get(AUTH_HEADER).iterator().next(),
                "The Authorization header value must be 'Bearer ' followed by the token.");
    }
}
