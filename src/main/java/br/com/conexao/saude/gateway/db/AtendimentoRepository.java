package br.com.conexao.saude.gateway.db;

import br.com.conexao.saude.gateway.db.jpa.AtendimentoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<AtendimentoEntity, Long> {
}
