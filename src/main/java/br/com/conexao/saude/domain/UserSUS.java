package br.com.conexao.saude.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserSUS {
    private Long id;
    private String name;
    private String cpf;
    private int age;
    private String gender;
    private Double weight;
    private Double height;
    private Double bmi;
    private String medicalConditions;
    private String allergies;
}