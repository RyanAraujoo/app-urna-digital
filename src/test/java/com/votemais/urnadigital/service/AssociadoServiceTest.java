package com.votemais.urnadigital.service;

import com.votemais.urnadigital.domain.Associado;
import com.votemais.urnadigital.domain.Voto;
import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.domain.records.RegisterDTO;
import com.votemais.urnadigital.repository.interfaces.AssociadoRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static com.votemais.urnadigital.domain.enums.RoleEnum.ADMIN;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AssociadoServiceTest {

    @Mock
    private AssociadoRepository associadoRepository;

    @InjectMocks
    private AssociadoService associadoService;

    @Test
    @DisplayName("Deve criar um associado com sucesso")
    void deveCriarAssociadoComSucesso() {

        RegisterDTO associadoDto =
                new RegisterDTO("Ryan", "Rua A", ADMIN, "12345678900","Ryan Araujo", "Rua Jose Maria 212", 18);

        String resultado = associadoService.criarAssociado(associadoDto);

        assertEquals("Associado criado com Sucesso!", resultado);

        verify(associadoRepository, times(1)).save(any(Associado.class));
    }

    @Test
    @DisplayName("Deve lançar exceção quando associado não for encontrado")
    void deveLancarExcecaoQuandoAssociadoNaoEncontrado() {

        UUID idAssociado = UUID.randomUUID();

        when(associadoRepository.findById(idAssociado))
                .thenReturn(Optional.empty());

        Exception exception = assertThrows(Exception.class, () -> {
            associadoService.votar(UUID.randomUUID(), idAssociado);
        });

        assertEquals("Associado não identificado.", exception.getMessage());
    }

    @Test
    @DisplayName("Deve realizar voto com sucesso")
    void deveRealizarVotoComSucesso() throws Exception {

        UUID idAssociado = UUID.randomUUID();
        UUID idPauta = UUID.randomUUID();

        Associado associado = mock(Associado.class);

        Set<Voto> votos = new HashSet<>();

        when(associadoRepository.findById(idAssociado))
                .thenReturn(Optional.of(associado));

        String resultado = associadoService.votar(idPauta, idAssociado);

        assertTrue(resultado.contains("Voto Realizado com Sucesso"));

        verify(associadoRepository, times(1))
                .save(associado);
    }

}