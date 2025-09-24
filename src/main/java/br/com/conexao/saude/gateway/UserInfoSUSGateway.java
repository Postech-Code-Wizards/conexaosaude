package br.com.conexao.saude.gateway;

import br.com.conexao.saude.domain.UserSUS;

import java.util.Optional;

public interface UserInfoSUSGateway {

    Optional<UserSUS> findByCpf(String cpf);

}