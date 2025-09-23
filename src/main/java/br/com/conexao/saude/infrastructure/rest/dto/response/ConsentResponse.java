package br.com.conexao.saude.infrastructure.rest.dto.response;

import java.time.ZonedDateTime;

public record ConsentResponse(
        Long id,
        String consentPurpose,
        String consentVersion,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}