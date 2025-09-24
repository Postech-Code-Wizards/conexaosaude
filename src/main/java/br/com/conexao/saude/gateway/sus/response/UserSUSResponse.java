package br.com.conexao.saude.gateway.sus.response;

public record UserSUSResponse(
        Long id,
        String name,
        String cpf,
        int age,
        String gender,
        Double weight,
        Double height,
        Double bmi,
        String medicalConditions,
        String allergies,
        String phone
) {}