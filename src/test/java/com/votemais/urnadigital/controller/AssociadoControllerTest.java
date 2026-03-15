package com.votemais.urnadigital.controller;

import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.service.AssociadoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadoControllerTest {

    @Mock
    private AssociadoService associadoService;

    @InjectMocks
    private AssociadoController associadoController;

    @Test
    @DisplayName("Deve Criar Associado")
    void criarAssociado() {

        AssociadoDTO associadoDto =
                new AssociadoDTO("Ryan", "Rua A", 21, "12345678900");

        when(associadoService.criarAssociado(associadoDto))
                .thenReturn("Associado ok");

        ResponseEntity<String> response = associadoController.criarAssociado(associadoDto);

        assertEquals(ResponseEntity.ok("Associado ok"), response);

        verify(associadoService, times(1)).criarAssociado(associadoDto);
    }

    @Test
    @DisplayName("Deve solicitar o Voto para o Associado")
    void votar() throws Exception {

        UUID idAssociado = UUID.randomUUID();
        UUID idPauta = UUID.randomUUID();

        when(associadoService.votar(idAssociado, idPauta))
                .thenReturn("Voto ok");

        ResponseEntity<String> response = associadoController.votar(idAssociado, idPauta);

        assertEquals(ResponseEntity.ok("Voto ok"), response);

        verify(associadoService, times(1)).votar(idAssociado, idPauta);
    }

    @Test
    @DisplayName("Deve retornar ao Usuario a Mensagem de Cliente não identificado")
    void votarComAssociadoNaoIdentificado() throws Exception {

        UUID idAssociado = UUID.randomUUID();
        UUID idPauta = UUID.randomUUID();

        when(associadoService.votar(idAssociado, idPauta))
                .thenThrow(new Exception("Usuario Não encontrado"));

        Exception associadoException = assertThrows(
                Exception.class,
                () -> associadoController.votar(idAssociado, idPauta)
        );

        assertEquals(associadoException.getMessage(),"Usuario Não encontrado");

        verify(associadoService, times(1)).votar(idAssociado, idPauta);
    }
}