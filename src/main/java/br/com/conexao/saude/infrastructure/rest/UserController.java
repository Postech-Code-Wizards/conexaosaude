package br.com.conexao.saude.infrastructure.rest;

import br.com.conexao.saude.application.facade.UserFacade;
import br.com.conexao.saude.infrastructure.rest.dto.request.UserRequest;
import br.com.conexao.saude.infrastructure.rest.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserFacade userFacade;

    @PostMapping
    ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest userRequest) {
        UserResponse userResponse = userFacade.register(userRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @GetMapping
    ResponseEntity<UserResponse> findByUser(@RequestParam(name = "cpf") String cpf) {
        UserResponse userResponse = userFacade.findByCpf(cpf);
        return ResponseEntity.ok().body(userResponse);
    }

}