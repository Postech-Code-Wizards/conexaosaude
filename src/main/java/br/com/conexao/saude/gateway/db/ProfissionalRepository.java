package br.com.conexao.saude.gateway.db;

import br.com.conexao.saude.gateway.db.jpa.ProfissionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfissionalRepository extends JpaRepository<ProfissionalEntity, Long> {
}
