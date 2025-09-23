package br.com.conexao.saude.domain;

import br.com.conexao.saude.domain.enums.SignatureDataType;
import br.com.conexao.saude.domain.enums.InterestContent;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import br.com.conexao.saude.domain.enums.SignatureStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Signature {
    private Long id;
    private User user;
    private Consent consent;
    private InterestContent interestContent;
    private NotificationFrequency notificationFrequency;
    private SignatureDataType signatureDataType;
    private boolean consentStatus;
    private SignatureStatus signatureStatus;
    private ZonedDateTime grantedAt;
    private ZonedDateTime updatedAt;
}