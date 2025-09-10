package br.com.conexao.saude.gateway.db.jpa;

import br.com.conexao.saude.domain.enums.AtendimentoDaConsulta;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.dialect.PostgreSQLEnumJdbcType;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Map;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "atendimento")
public class AtendimentoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "paciente_id")
    private PacienteEntity paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id")
    private ProfissionalEntity profissional;

    @ManyToOne
    @JoinColumn(name = "unidade_id")
    private UnidadeSaudeEntity unidadeSaude;

    @Column(name = "data_atendimento", nullable = false)
    private LocalDateTime dataAtendimento;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "tipo_de_atendimento")
    private AtendimentoDaConsulta atendimentoDaConsulta;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "detalhes", columnDefinition = "jsonb")
    private Map<String, Object> detalhes;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private ZonedDateTime atualizadoEm;
}
