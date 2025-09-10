package br.com.conexao.saude.gateway.db.jpa;

import br.com.conexao.saude.domain.enums.TipoDeExame;
import jakarta.persistence.*;
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
@Table(name = "exame")
public class ExameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "atendimento_id")
    private AtendimentoEntity atendimento;

    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    @Column(name = "tipo_de_exame")
    private TipoDeExame tipoDeExame;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name= "resultado", columnDefinition = "jsonb")
    private Map<String, Object> resultado; // usado map pois poderá aparecer par chave valor no JSON

    @Column(name = "realizado_em")
    private LocalDateTime realizadoEm;

    @CreationTimestamp
    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @UpdateTimestamp
    @Column(name = "atualizado_em")
    private ZonedDateTime atualizadoEm;
}
