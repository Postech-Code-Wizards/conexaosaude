package br.com.conexao.saude.gateway.database.repositories;

import br.com.conexao.saude.gateway.database.entities.SignatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SignatureRepository extends JpaRepository<SignatureEntity, Long> {
}
