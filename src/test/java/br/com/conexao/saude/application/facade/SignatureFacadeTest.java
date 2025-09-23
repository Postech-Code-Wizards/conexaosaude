package br.com.conexao.saude.application.facade;

import br.com.conexao.saude.application.facade.converter.SignatureRequestToDomain;
import br.com.conexao.saude.application.usecase.SignatureUseCase;
import br.com.conexao.saude.domain.Signature;
import br.com.conexao.saude.infrastructure.rest.dto.request.SignatureRequest;
import org.instancio.Instancio;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SignatureFacadeTest {

    @Mock
    private SignatureRequestToDomain signatureRequestToDomain;

    @Mock
    private SignatureUseCase signatureUseCase;

    @InjectMocks
    private SignatureFacade signatureFacade;

    @Test
    @DisplayName("sign should convert request and delegate signing to use case")
    void sign_convertsRequestAndDelegatesToUseCase() {
        SignatureRequest request = Instancio.create(SignatureRequest.class);
        Signature domain = Instancio.create(Signature.class);

        when(signatureRequestToDomain.execute(request)).thenReturn(domain);

        signatureFacade.sign(request);

        verify(signatureRequestToDomain).execute(request);
        verify(signatureUseCase).sign(domain);
        verifyNoMoreInteractions(signatureRequestToDomain, signatureUseCase);
    }
}