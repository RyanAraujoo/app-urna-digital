package com.votemais.urnadigital.controller;

import com.votemais.urnadigital.controller.interfaces.AssociadoControllerInterface;
import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping(value = "/associado")
@ControllerAdvice
public class AssociadoController implements AssociadoControllerInterface {

    @Autowired
    AssociadoService associadoService;

    @Override
    @PostMapping
    public ResponseEntity<String> criarAssociado(AssociadoDTO associadoDto) {
        String msg = this.associadoService.criarAssociado(associadoDto);
        return ResponseEntity.ok(msg);
    }

    @Override
    @PostMapping("/votar")
    public ResponseEntity<String> votar(UUID idPauta, UUID idAssociado) throws Exception {
        String msg = this.associadoService.votar(idPauta,idAssociado);
        return ResponseEntity.ok(msg);
    }
}
