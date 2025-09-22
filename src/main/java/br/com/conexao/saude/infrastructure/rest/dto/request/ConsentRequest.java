package br.com.conexao.saude.infrastructure.rest.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ConsentRequest(

        @NotBlank(message = "Consent purpose cannot be empty.")
        @Size(max = 2000, message = "Consent purpose must not exceed 255 characters.")
        String consentPurpose,

        @NotBlank(message = "Consent version cannot be empty.")
        @Size(max = 50, message = "Consent version must not exceed 50 characters.")
        String consentVersion
) {}