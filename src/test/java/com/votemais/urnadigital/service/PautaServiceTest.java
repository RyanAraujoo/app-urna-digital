package com.votemais.urnadigital.service;

import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import com.votemais.urnadigital.repository.interfaces.PautaRepository;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PautaServiceTest {

    @Mock
    PautaRepository pautaRepository;

    @Autowired
    @InjectMocks
    PautaService pautaService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("Deve Encerrar Pauta e Contabilizar os Votos da Urna")
    void encerrar() {
        PautaDAO pauta = new PautaDAO("EntregaMais","Av.PresidenteDutra301",60,90,100, StatusAberturaEnum.ABERTO);
        UUID pautaID = UUID.randomUUID();
        when(this.pautaRepository.findById(pautaID)).thenReturn(Optional.of(pauta));

        String retorno = this.pautaService.encerrar(pautaID);

        verify(this.pautaRepository,times(1)).AlteraStatusPauta(any(),any());

        assertThat(retorno).isEqualTo("A pauta solicitada foi encerrada. Segue o retorno: Sim: 90 || Não: 100");
    }

    @Test
    @DisplayName("Deve Abrir uma Pauta para Permitir Contabilizacao de Votos")
    void abrir() {
        UUID pautaID = UUID.randomUUID();
        this.pautaService.abrir(pautaID);
        verify(this.pautaRepository,times(1)).AlteraStatusPauta(any(),any());
    }

    @Test
    @DisplayName("Deve permitir cadastrar uma pauta para votacao")
    void cadastrar() {
        PautaDTO pauta = new PautaDTO("EntregaMais1","Av.PresidenteDutra301",60);
        this.pautaService.cadastrar(pauta);
        verify(this.pautaRepository,times(1)).save(any());
    }
}