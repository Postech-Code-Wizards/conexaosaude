package br.com.conexao.saude.application.facade.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.infrastructure.rest.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDomainToResponse {

    public UserResponse execute(User user){
        return new UserResponse(
                user.getId(),
                user.getFullName(),
                user.getCpf(),
                user.getWhatsappNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
