package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.ContentComposerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentComposerUseCase {

    private final ContentComposerGateway contentComposerGateway;

    public String execute(User user) {

        StringBuilder prompt = new StringBuilder();
        prompt.append("Gere uma recomendação de alimentação saudável para o user do SUS.\n");
        prompt.append(", Idade: ").append(user.getAge()).append(" anos.\n");

        // todo: sexo, peso, altura, nível de atividade física
        // todo: gerar aletoariamente mock com essas inforamações
        if (user.getAllergies() != null && user.getAllergies().length > 0) {
            prompt.append("Alergias: ");
            for (String alergia : user.getAllergies()) {
                prompt.append(alergia).append(" ");
            }
            prompt.append("\n");
        }
        prompt.append("A response deve estar de acordo com as políticas de saúde pública, ");
        prompt.append("evitando riscos à saúde e sempre considerando idosos, \n");
        prompt.append("A resposta precisa ser em pt-br, não pode ter quebra de linha, não pode ter mais de 1024 caracteres e não repetir a resposta anterior \n");

        return contentComposerGateway.generateMessage(prompt.toString());
    }

}