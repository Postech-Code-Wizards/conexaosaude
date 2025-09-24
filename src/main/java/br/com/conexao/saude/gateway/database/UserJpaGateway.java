package br.com.conexao.saude.gateway.database;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.gateway.UserGateway;
import br.com.conexao.saude.gateway.database.converter.UserDomainToEntity;
import br.com.conexao.saude.gateway.database.converter.UserEntityToDomain;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
import br.com.conexao.saude.gateway.database.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserJpaGateway implements UserGateway {

    private final UserRepository userRepository;
    private final UserDomainToEntity userDomainToEntity;
    private final UserEntityToDomain userEntityToDomain;

    @Override
    public User save(User user) {
        UserEntity userEntity = userDomainToEntity.execute(user);
        UserEntity userEntitySaved = userRepository.save(userEntity);
        return userEntityToDomain.execute(userEntitySaved);
    }

    @Override
    public User findByCpf(String cpf) {
        final UserEntity userEntity = userRepository.findByCpf(cpf)
                .orElseThrow(() -> new RuntimeException("User not found."));
        return userEntityToDomain.execute(userEntity);
    }

    @Override
    public User findById(Long id) {
        final UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found."));
        return userEntityToDomain.execute(userEntity);
    }

    @Override
    public List<User> findAllByNotificationFrequencyOfSignature(NotificationFrequency notificationFrequency) {
        List<UserEntity> userEntities = userRepository.findAllByNotificationFrequencyOfSignature(notificationFrequency);
        return userEntities.stream()
                .map(userEntityToDomain::execute)
                .toList();
    }

}