package br.com.conexao.saude.infrastructure.rest;

import br.com.conexao.saude.application.facade.ConsentFacade;
import br.com.conexao.saude.infrastructure.rest.dto.request.ConsentRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.ConsentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/consent")
public class ConsentController {

    private final ConsentFacade consentFacade;

    @GetMapping
    ResponseEntity<ConsentResponse> findLastConsent() {
        ConsentResponse consentResponse = consentFacade.findLastConsent();
        return ResponseEntity.ok().body(consentResponse);
    }

    @PostMapping
    ResponseEntity<ConsentResponse> createConsent(@RequestBody @Valid ConsentRequest consentRequest) {
        ConsentResponse consentResponse = consentFacade.createConsent(consentRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(consentResponse);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteConsent(@PathVariable Long id) {
        consentFacade.deleteConsent(id);
        return ResponseEntity.noContent().build();
    }

}
