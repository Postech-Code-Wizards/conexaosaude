package br.com.conexao.saude.infrastructure.rest.dto.response;

import java.time.ZonedDateTime;

public record UserResponse(
        Long id,
        String fullName,
        String cpf,
        String whatsappNumber,
        ZonedDateTime createdAt,
        ZonedDateTime updatedAt
) {}