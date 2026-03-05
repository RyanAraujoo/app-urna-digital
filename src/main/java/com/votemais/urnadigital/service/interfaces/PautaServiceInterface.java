package com.votemais.urnadigital.service.interfaces;

import com.votemais.urnadigital.domain.dtos.PautaDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface PautaServiceInterface {
    String encerrar(UUID idPauta);

    String abrir(UUID idPauta);

    String cadastrar(PautaDTO pauta);
}
