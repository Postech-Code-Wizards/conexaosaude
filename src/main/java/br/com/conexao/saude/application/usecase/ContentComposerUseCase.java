package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.UserSUS;
import br.com.conexao.saude.gateway.ContentComposerGateway;
import br.com.conexao.saude.gateway.UserInfoSUSGateway;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContentComposerUseCase {

    private final ContentComposerGateway contentComposerGateway;
    private final UserInfoSUSGateway userInfoSUSGateway;

    public String execute(User user) {

        Optional<UserSUS> userSUSOptional = userInfoSUSGateway.findByCpf(user.getCpf());
        String userInformationDetails = definePromptWithUserSUS(userSUSOptional.orElse(null));
        String prompt = String.format("""
            Gere uma recomendação de alimentação saudável para o usuário do SUS.
            %s
            A resposta deve estar de acordo com as políticas de saúde pública,
            evitando riscos à saúde e sempre considerando idosos.
            A resposta precisa ser em pt-br, não pode ter quebra de linha,
            não pode ter mais de 1024 caracteres e não pode repetir a resposta anterior.
            """, userInformationDetails);

        return contentComposerGateway.generateMessage(prompt);

    }

    private String definePromptWithUserSUS(UserSUS userSUS) {

        if(userSUS == null) {
            log.warn("UserSUS information not found.");
            return null;
        }

        StringBuilder details = new StringBuilder();
        details.append("Dados do Paciente:\n");
        appendIfNotNull(details, "Idade", userSUS.getAge() + " anos");
        appendIfNotNull(details, "Sexo", userSUS.getGender());
        appendIfNotNull(details, "Altura", userSUS.getHeight() + " cm");
        appendIfNotNull(details, "Peso", userSUS.getWeight() + " kg");
        appendIfNotNull(details, "IMC", userSUS.getBmi());
        appendIfNotNull(details, "Condições de saúde", userSUS.getMedicalConditions());
        appendIfNotNull(details, "Alergias alimentares", userSUS.getAllergies());
        return details.toString();

    }

    private void appendIfNotNull(StringBuilder builder, String label, Object value) {
        if (value != null) {
            builder.append("- ").append(label).append(": ").append(value).append("\n");
        }
    }
}