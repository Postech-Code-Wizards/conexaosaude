package br.com.conexao.saude.gateway.db;

import br.com.conexao.saude.gateway.db.jpa.ExameEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExameRepository extends JpaRepository<ExameEntity, Long> {
}
