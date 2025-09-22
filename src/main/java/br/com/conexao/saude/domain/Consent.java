package br.com.conexao.saude.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@Getter
@AllArgsConstructor
public class Consent {
    private Long id;
    private String consentPurpose;
    private String consentVersion;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
}
