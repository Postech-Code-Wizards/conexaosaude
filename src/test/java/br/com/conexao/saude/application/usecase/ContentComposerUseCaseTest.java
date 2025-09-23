package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.ContentComposerGateway;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("ContentComposerUseCase Tests")
class ContentComposerUseCaseTest {

    @InjectMocks
    private ContentComposerUseCase contentComposerUseCase;

    @Mock
    private ContentComposerGateway contentComposerGateway;

    @Test
    @DisplayName("Should generate a healthy eating recommendation for a SUS user")
    void execute_generatesHealthyEatingRecommendation() {
        User user = Instancio.create(User.class);
        String expectedMessage = "Recomendação de alimentação saudável";

        when(contentComposerGateway.generateMessage(org.mockito.ArgumentMatchers.anyString())).thenReturn(expectedMessage);

        String actualMessage = contentComposerUseCase.execute(user);

        assertThat(actualMessage).isEqualTo(expectedMessage);
    }
}