package br.com.conexao.saude.gateway.database.repositories;

import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.gateway.database.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findByCpf(String cpf);

    @Query("SELECT u FROM UserEntity u " +
            "JOIN u.signatureEntities s " +
            "WHERE s.notificationFrequency = :notificationFrequency " +
            "AND s.consentStatus = true " +
            "AND s.signatureStatus = SignatureStatus.ACTIVE")
    List<UserEntity> findAllByNotificationFrequencyOfSignature(NotificationFrequency notificationFrequency);

}
