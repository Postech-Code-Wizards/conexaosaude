package br.com.conexao.saude.gateway.database.repositories;

import br.com.conexao.saude.gateway.database.entities.ConsentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsentRepository extends JpaRepository<ConsentEntity, Long> {

    Optional<ConsentEntity> findFirstByOrderByConsentVersionDesc();

}
