package com.votemais.urnadigital.service.interfaces;

import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.domain.records.RegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AssociadoServiceInterface {
    String criarAssociado(RegisterDTO associadoDto);
    String votar(UUID idPauta, UUID idAssociado) throws Exception;
}
