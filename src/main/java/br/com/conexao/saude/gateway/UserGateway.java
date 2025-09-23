package br.com.conexao.saude.gateway;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.NotificationFrequency;

import java.util.List;

public interface UserGateway {

    User save(User user);

    User findByCpf(String cpf);

    User findById(Long id);

    List<User> findAllByNotificationFrequencyOfSignature(NotificationFrequency notificationFrequency);

}
