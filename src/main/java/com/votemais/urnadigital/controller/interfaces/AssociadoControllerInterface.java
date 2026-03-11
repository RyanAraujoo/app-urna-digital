package com.votemais.urnadigital.controller.interfaces;
import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AssociadoControllerInterface {
     ResponseEntity<String> criarAssociado(AssociadoDTO associadoDto);
     ResponseEntity<String> votar(UUID idPauta, UUID idAssociado) throws Exception;
}
