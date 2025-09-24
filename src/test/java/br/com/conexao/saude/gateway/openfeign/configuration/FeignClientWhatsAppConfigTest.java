package br.com.conexao.saude.gateway.openfeign.configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {"whatsapp.service.token=test-token-1234"})
class FeignClientWhatsAppConfigTest {

    @Autowired
    private RequestInterceptor requestInterceptor;

    @Test
    @DisplayName("should add authorization header with token to request template")
    void requestInterceptor_GivenToken_ShouldAddAuthorizationHeader() {
        RequestTemplate requestTemplate = new RequestTemplate();
        String expectedHeader = "Bearer test-token-1234";

        requestInterceptor.apply(requestTemplate);

        assertEquals(expectedHeader, requestTemplate.headers().get("Authorization").iterator().next());
    }
}