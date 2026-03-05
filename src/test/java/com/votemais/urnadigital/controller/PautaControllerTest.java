package com.votemais.urnadigital.controller;

import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.service.PautaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class PautaControllerTest {

    @Mock
    PautaService pautaService;

    @Autowired
    @InjectMocks
    PautaController pautaController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve receber uma solicitacao de Encerramento de Pauta")
    void encerrar() {
        UUID uuid = UUID.randomUUID();
        ResponseEntity retn = this.pautaController.encerrar(String.valueOf(uuid));

        when(this.pautaService.encerrar(any())).thenReturn("A pauta solicitada foi encerrada. Segue o retorno: XXXXX");

        assertEquals(retn.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve receber uma solicitacao de Abertura de Pauta")
    void abrir() {
        UUID uuid = UUID.randomUUID();
        ResponseEntity retn = this.pautaController.abrir(String.valueOf(uuid));

        when(this.pautaService.abrir(any())).thenReturn("A pauta solicitada foi aberta. Segue o retorno: XXXXX");

        assertEquals(retn.getStatusCode(), HttpStatus.OK);
    }

    @Test
    @DisplayName("Deve receber uma solicitacao de Cadastro de Pauta")
    void cadastrar() {
        PautaDTO pauta = new PautaDTO("EntregaMais1","Av.PresidenteDutra301",60);
        ResponseEntity retn = this.pautaController.cadastrar(pauta);

        when(this.pautaService.cadastrar(any())).thenReturn("A pauta cadastrada com sucesso. Segue o retorno: XXXXX");

        assertEquals(retn.getStatusCode(), HttpStatus.OK);
    }
}