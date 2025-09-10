package br.com.conexao.saude.gateway.db;

import br.com.conexao.saude.gateway.db.jpa.PacienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteEntity, Long> {
}
