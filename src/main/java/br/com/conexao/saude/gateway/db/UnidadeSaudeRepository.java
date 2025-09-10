package br.com.conexao.saude.gateway.db;

import br.com.conexao.saude.gateway.db.jpa.UnidadeSaudeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeSaudeRepository extends JpaRepository<UnidadeSaudeEntity, Long> {
}
