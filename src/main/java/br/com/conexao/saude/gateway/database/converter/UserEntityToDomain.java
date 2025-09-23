package br.com.conexao.saude.gateway.database.converter;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserEntityToDomain {

    public User execute(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getFullName(),
                userEntity.getCpf(),
                null,
                null,
                userEntity.getWhatsappNumber(),
                userEntity.getCreatedAt(),
                userEntity.getUpdatedAt());
    }
}
