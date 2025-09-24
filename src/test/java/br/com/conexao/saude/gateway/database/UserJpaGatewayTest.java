package br.com.conexao.saude.gateway.database;

import br.com.conexao.saude.domain.User;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.gateway.database.converter.UserDomainToEntity;
import br.com.conexao.saude.gateway.database.converter.UserEntityToDomain;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
import br.com.conexao.saude.gateway.database.repositories.UserRepository;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserJpaGatewayTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserDomainToEntity userDomainToEntity;
    @Mock
    private UserEntityToDomain userEntityToDomain;

    @InjectMocks
    private UserJpaGateway userJpaGateway;

    @Test
    @DisplayName("should save a user")
    void save_GivenUser_ShouldReturnSavedUser() {
        User user = Instancio.create(User.class);
        UserEntity userEntity = Instancio.create(UserEntity.class);
        UserEntity userEntitySaved = Instancio.create(UserEntity.class);
        User userSaved = Instancio.create(User.class);

        when(userDomainToEntity.execute(any(User.class))).thenReturn(userEntity);
        when(userRepository.save(any(UserEntity.class))).thenReturn(userEntitySaved);
        when(userEntityToDomain.execute(any(UserEntity.class))).thenReturn(userSaved);

        User result = userJpaGateway.save(user);

        assertNotNull(result);
        assertEquals(userSaved, result);

        verify(userDomainToEntity).execute(user);
        verify(userRepository).save(userEntity);
        verify(userEntityToDomain).execute(userEntitySaved);
    }

    @Test
    @DisplayName("should find a user by cpf")
    void findByCpf_GivenCpf_ShouldReturnUser() {
        String cpf = "12345678900";
        UserEntity userEntity = Instancio.create(UserEntity.class);
        User user = Instancio.create(User.class);

        when(userRepository.findByCpf(cpf)).thenReturn(Optional.of(userEntity));
        when(userEntityToDomain.execute(any(UserEntity.class))).thenReturn(user);

        User result = userJpaGateway.findByCpf(cpf);

        assertNotNull(result);
        assertEquals(user, result);

        verify(userRepository).findByCpf(cpf);
        verify(userEntityToDomain).execute(userEntity);
    }

    @Test
    @DisplayName("should throw exception when user not found by cpf")
    void findByCpf_GivenNonExistentCpf_ShouldThrowException() {
        String cpf = "99999999999";
        when(userRepository.findByCpf(cpf)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userJpaGateway.findByCpf(cpf));

        verify(userRepository).findByCpf(cpf);
        verifyNoInteractions(userEntityToDomain);
    }

    @Test
    @DisplayName("should find a user by id")
    void findById_GivenId_ShouldReturnUser() {
        Long id = 1L;
        UserEntity userEntity = Instancio.create(UserEntity.class);
        User user = Instancio.create(User.class);

        when(userRepository.findById(id)).thenReturn(Optional.of(userEntity));
        when(userEntityToDomain.execute(any(UserEntity.class))).thenReturn(user);

        User result = userJpaGateway.findById(id);

        assertNotNull(result);
        assertEquals(user, result);

        verify(userRepository).findById(id);
        verify(userEntityToDomain).execute(userEntity);
    }

    @Test
    @DisplayName("should throw exception when user not found by id")
    void findById_GivenNonExistentId_ShouldThrowException() {
        Long id = 99L;
        when(userRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> userJpaGateway.findById(id));

        verify(userRepository).findById(id);
        verifyNoInteractions(userEntityToDomain);
    }

    @Test
    @DisplayName("should find all users by notification frequency")
    void findAllByNotificationFrequencyOfSignature_GivenFrequency_ShouldReturnUserList() {
        NotificationFrequency frequency = NotificationFrequency.DAILY;
        List<UserEntity> userEntities = Instancio.ofList(UserEntity.class).size(3).create();
        List<User> users = Instancio.ofList(User.class).size(3).create();

        when(userRepository.findAllByNotificationFrequencyOfSignature(frequency)).thenReturn(userEntities);
        when(userEntityToDomain.execute(any(UserEntity.class))).thenReturn(users.get(0), users.get(1), users.get(2));

        List<User> result = userJpaGateway.findAllByNotificationFrequencyOfSignature(frequency);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(users, result);

        verify(userRepository).findAllByNotificationFrequencyOfSignature(frequency);
        verify(userEntityToDomain, times(3)).execute(any(UserEntity.class));
    }

    @Test
    @DisplayName("should return an empty list when no users are found by notification frequency")
    void findAllByNotificationFrequencyOfSignature_GivenNoUsers_ShouldReturnEmptyList() {
        NotificationFrequency frequency = NotificationFrequency.WEEKLY;

        when(userRepository.findAllByNotificationFrequencyOfSignature(frequency)).thenReturn(Collections.emptyList());

        List<User> result = userJpaGateway.findAllByNotificationFrequencyOfSignature(frequency);

        assertNotNull(result);
        assertTrue(result.isEmpty());

        verify(userRepository).findAllByNotificationFrequencyOfSignature(frequency);
        verifyNoInteractions(userEntityToDomain);
    }
}