package com.danilodev.apiCep.domain.service;

import com.danilodev.apiCep.domain.dto.CepResponseDTO;
import com.danilodev.apiCep.domain.model.CepLog;
import com.danilodev.apiCep.domain.model.CepLogStorage;
import com.danilodev.apiCep.domain.validators.CepValidator;
import com.danilodev.apiCep.integration.CepLookup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CepServiceTest {

    @Mock
    private CepLogStorage cepLogStorage;

    @Mock
    private CepLookup cepLookup;

    @Mock
    private CepValidator cepValidator;

    @InjectMocks
    private CepService cepService;

    private final String MOCK_CEP = "12345678";
    private final String RESPONSE_DATA = """
        {"cep":"12345678","logradouro":"Rua Exemplo",
        "bairro":"Centro","cidade":"São Paulo","estado":"SP"}""";


    @Test
    void buscarCep_DeveRetornarRespostaValidaELogarNoBanco() {

        doNothing().when(cepValidator).validate(MOCK_CEP);

        CepResponseDTO response = cepService.buscarCep(MOCK_CEP);

        assertEquals(MOCK_CEP, response.cep());
        assertNotNull(response.timestamp());


        verify(cepLogStorage, times(1)).salvar(any(CepLog.class));
    }

    @Test
    void buscarCep_DeveLancarExcecao_ParaCepInvalido() {
        String cepInvalido = "12345";
        doThrow(new IllegalArgumentException("CEP Inválido")).when(cepValidator).validate(cepInvalido);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> cepService.buscarCep(cepInvalido));

        assertEquals("CEP Inválido", exception.getMessage());


        verify(cepLogStorage, never()).salvar(any(CepLog.class));
    }
}
