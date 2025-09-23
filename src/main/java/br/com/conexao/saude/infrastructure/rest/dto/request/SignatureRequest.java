package br.com.conexao.saude.infrastructure.rest.dto.request;

import br.com.conexao.saude.domain.enums.SignatureDataType;
import br.com.conexao.saude.domain.enums.InterestContent;
import br.com.conexao.saude.domain.enums.NotificationFrequency;
import jakarta.validation.constraints.NotNull;

public record SignatureRequest(

        @NotNull(message = "User ID cannot be null.")
        Long userId,

        @NotNull(message = "Consent ID cannot be null.")
        Long consentId,

        @NotNull(message = "Notification frequency cannot be null.")
        NotificationFrequency notificationFrequency,

        @NotNull(message = "Data type cannot be null.")
        SignatureDataType signatureDataType,

        @NotNull(message = "Interest content cannot be null.")
        InterestContent interestContent
) {}