package br.com.conexao.saude.infrastructure.rest;

import br.com.conexao.saude.application.facade.SignatureFacade;
import br.com.conexao.saude.infrastructure.rest.dto.request.SignatureRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/signature")
public class SignatureController {

    private final SignatureFacade signatureFacade;

    @PostMapping
    ResponseEntity<Void> sign(@RequestBody @Valid SignatureRequest signatureRequest) {
        signatureFacade.sign(signatureRequest);
        return ResponseEntity.noContent().build();
    }

}