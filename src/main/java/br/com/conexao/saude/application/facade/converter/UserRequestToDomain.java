package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.infrastructure.rest.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRequestToDomain {

    public User execute(UserRequest userRequest){
        return new User(
                null,
                userRequest.fullName(),
                userRequest.cpf(),
                null,
                null,
                userRequest.whatsappNumber(),
                null,
                null
        );
    }
}
