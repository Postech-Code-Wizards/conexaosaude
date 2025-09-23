package br.com.conexao.saude.infrastructure.rest.dto.request;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record UserRequest(

        @NotBlank(message = "Full name cannot be blank")
        @Size(min = 2, max = 255, message = "Full name must have between 2 and 255 characters")
        String fullName,

        @NotBlank(message = "CPF cannot be blank")
        @Size(min = 11, max = 11, message = "CPF must have 11 digits")
        @Pattern(regexp = "^\\d{11}$", message = "CPF must contain only numbers")
        String cpf,

        @NotBlank(message = "WhatsApp number cannot be blank")
        @Size(min = 8, max = 20, message = "WhatsApp number must have between 8 and 20 digits")
        String whatsappNumber
) {}