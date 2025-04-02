package com.danilodev.apiCep.domain.service;

import com.danilodev.apiCep.domain.dto.CepResponseDTO;
import com.danilodev.apiCep.domain.model.CepLog;
import com.danilodev.apiCep.domain.repository.CepLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CepServiceTest {

    @Mock
    private CepLogRepository cepLogRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CepService cepService;

    private final String MOCK_CEP = "12345678";
    private final String MOCK_RESPONSE = """
        {
            "cep": "12345678",
            "logradouro": "Rua Exemplo",
            "bairro": "Centro",
            "cidade": "São Paulo",
            "estado": "SP"
        }
        """;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        // Simulando a resposta do RestTemplate SEM precisar do Mockoon
        when(restTemplate.getForObject("http://localhost:8081/cep/" + MOCK_CEP, String.class))
                .thenReturn(MOCK_RESPONSE);
    }

    @Test
    void buscarCep_DeveRetornarRespostaValidaELogarNoBanco() {
        // Chama o serviço
        CepResponseDTO response = cepService.buscarCep(MOCK_CEP);

        // Valida que o retorno está correto
        assertEquals(MOCK_CEP, response.cep());

        // Verifica se o log foi salvo no banco
        verify(cepLogRepository, times(1)).save(any(CepLog.class));
    }

    @Test
    void buscarCep_DeveLancarExcecao_ParaCepInvalido() {
        String cepInvalido = "12345"; // CEP inválido

        // Verifica se a exceção IllegalArgumentException é lançada
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            cepService.buscarCep(cepInvalido);
        });

        assertEquals("CEP Inválido", exception.getMessage());

        // Verifica que nada foi salvo no banco
        verify(cepLogRepository, never()).save(any(CepLog.class));
    }
}
