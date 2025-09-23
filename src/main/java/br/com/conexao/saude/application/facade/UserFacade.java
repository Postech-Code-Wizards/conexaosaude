package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.facade.converter.UserDomainToResponse;
import br.com.conexao.saude.application.facade.converter.UserRequestToDomain;
import br.com.conexao.saude.application.usecase.UserUseCase;
import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.infrastructure.rest.dto.request.UserRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserUseCase userUseCase;
    private final UserRequestToDomain userRequestToDomain;
    private final UserDomainToResponse userDomainToResponse;

    public UserResponse register(UserRequest userRequest){
        User user = userRequestToDomain.execute(userRequest);
        User userSaved = userUseCase.register(user);
        return userDomainToResponse.execute(userSaved);
    }

    public UserResponse findByCpf(String cpf){
        User user = userUseCase.findByCpf(cpf);
        return userDomainToResponse.execute(user);
    }

}
