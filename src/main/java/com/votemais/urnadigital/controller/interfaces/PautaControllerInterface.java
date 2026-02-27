package com.votemais.urnadigital.controller.interfaces;

import com.votemais.urnadigital.domain.dtos.PautaDTO;
import org.springframework.http.ResponseEntity;

public interface PautaControllerInterface {
    ResponseEntity<String> encerrar(String idPauta);
    ResponseEntity<String> abrir(String idPauta);
    ResponseEntity<String> cadastrar(PautaDTO pauta);
}
