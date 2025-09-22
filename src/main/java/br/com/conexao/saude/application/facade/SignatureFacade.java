package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.facade.converter.SignatureRequestToDomain;
import br.com.conexao.saude.application.usecase.SignatureUseCase;
import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.infrastructure.rest.dto.request.SignatureRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SignatureFacade {

    private final SignatureRequestToDomain signatureRequestToDomain;
    private final SignatureUseCase signatureUseCase;

    public void sign(SignatureRequest signatureRequest){
        Signature signature = signatureRequestToDomain.execute(signatureRequest);
        signatureUseCase.sign(signature);
    }

}