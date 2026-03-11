package com.votemais.urnadigital.service.interfaces;

import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AssociadoServiceInterface {
    String criarAssociado(AssociadoDTO associadoDto);
    String votar(UUID idPauta, UUID idAssociado) throws Exception;
}
