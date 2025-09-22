package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.gateway.ContentComposerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentComposerUseCase {

    private final ContentComposerGateway contentComposerGateway;

    public String execute(String prompt) {
        return contentComposerGateway.generate(prompt);
    }

}