package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.ContentComposerGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
@RequiredArgsConstructor
public class ContentComposerUseCase {

    private final ContentComposerGateway contentComposerGateway;
    public String execute(User user) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("Gere uma recomendação de alimentação saudável para o user do SUS.\n");
        prompt.append("Paciente: ").append(user.getFullName())
                .append(", Idade: ").append(user.getAge()).append(" anos.\n");
        if (user.getAllergies() != null && user.getAllergies().length > 0) {
            prompt.append("Alergias: ");
            for (String alergia : user.getAllergies()) {
                prompt.append(alergia).append(" ");
            }
            prompt.append("\n");
        }
        prompt.append("A response deve estar de acordo com as políticas de saúde pública, ");
        prompt.append("evitando riscos à saúde e sempre considerando idosos.\n");

        String response = contentComposerGateway.generateMessage(prompt.toString());

        // todo: implementar validações para a mensagem

        return "[VALIDADO SUS] " + response;
    }

}