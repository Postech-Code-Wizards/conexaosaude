package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserDomainToEntity {

    public UserEntity convert(User user) {
        return new UserEntity(
                user.getId(),
                user.getFullName(),
                user.getCpf(),
                user.getWhatsappNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt(),
                null);
    }
}
