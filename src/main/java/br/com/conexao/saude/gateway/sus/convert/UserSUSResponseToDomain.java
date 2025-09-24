package br.com.conexao.saude.gateway.sus.convert;

import br.com.conexao.saude.domain.UserSUS;
import br.com.conexao.saude.gateway.sus.response.UserSUSResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSUSResponseToDomain {

    public UserSUS execute(UserSUSResponse userSUSResponse) {
        return new UserSUS(
                userSUSResponse.id(),
                userSUSResponse.name(),
                userSUSResponse.cpf(),
                userSUSResponse.age(),
                userSUSResponse.gender(),
                userSUSResponse.weight(),
                userSUSResponse.height(),
                userSUSResponse.bmi(),
                userSUSResponse.medicalConditions(),
                userSUSResponse.allergies()
        );
    }

}