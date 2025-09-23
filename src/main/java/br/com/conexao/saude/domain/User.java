package br.com.conexao.saude.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class User {
    private Long id;
    private String fullName;
    private String cpf;
    private String age;
    private String[] allergies;
    private String whatsappNumber;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}