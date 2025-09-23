package br.com.conexao.saude.gateway.database.entities;

import br.com.conexao.saude.domain.enums.SignatureDataType;
import br.com.conexao.saude.domain.enums.InterestContent;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.domain.enums.SignatureStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "signatures", indexes = {
        @Index(name = "idx_signatures_user_id", columnList = "user_id"),
        @Index(name = "idx_signatures_interest_content", columnList = "interest_content")
})
public class SignatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_user"))
    private UserEntity userEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "consent_id", nullable = false, foreignKey = @ForeignKey(name = "fk_consent"))
    private ConsentEntity consentEntity;

    @Enumerated(EnumType.STRING)
    @Column(name = "interest_content", nullable = false, length = 50)
    private InterestContent interestContent;

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_frequency", nullable = false, length = 10)
    private NotificationFrequency notificationFrequency;

    @Enumerated(EnumType.STRING)
    @Column(name = "data_type", nullable = false, length = 10)
    private SignatureDataType signatureDataType;

    @Column(name = "consent_status", nullable = false)
    private boolean consentStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private SignatureStatus signatureStatus;

    @Column(name = "granted_at", nullable = false)
    private ZonedDateTime grantedAt;

    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.grantedAt = ZonedDateTime.now();
        this.updatedAt = ZonedDateTime.now();
        if (this.signatureStatus == null) {
            this.signatureStatus = SignatureStatus.ACTIVE;
        }
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = ZonedDateTime.now();
    }

}