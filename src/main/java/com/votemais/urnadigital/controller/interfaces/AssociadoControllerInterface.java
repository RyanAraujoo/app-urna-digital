package com.votemais.urnadigital.controller.interfaces;
import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.domain.records.RegisterDTO;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface AssociadoControllerInterface {
     ResponseEntity<String> criarAssociado(RegisterDTO associadoDto);
     ResponseEntity<String> votar(UUID idPauta, UUID idAssociado) throws Exception;
}
