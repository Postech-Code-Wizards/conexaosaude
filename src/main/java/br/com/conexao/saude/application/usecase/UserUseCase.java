package br.com.conexao.saude.application.usecase;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.gateway.UserGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserUseCase {

    private final UserGateway userGateway;

    public User register(User user){
        return userGateway.save(user);
    }

    public User findByCpf(String cpf) {
        return userGateway.findByCpf(cpf);
    }
}
