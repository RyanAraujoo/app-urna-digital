package com.votemais.urnadigital.controller;

import com.votemais.urnadigital.controller.interfaces.PautaControllerInterface;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PautaController implements PautaControllerInterface {

    @Autowired
    private PautaService pautaService;

    @Override
    public ResponseEntity<String> encerrar(String idPauta) {
        String msgRetorno = pautaService.encerrar(idPauta);
        return ResponseEntity.ok().body(msgRetorno);
    }

    @Override
    public ResponseEntity<String> abrir(String idPauta) {
        String msgRetorno = pautaService.abrir(idPauta);
        return ResponseEntity.ok().body(msgRetorno);
    }

    @Override
    public ResponseEntity<String> cadastrar(PautaDTO pauta) {
        String msgRetorno = pautaService.cadastrar(pauta);
        return ResponseEntity.ok().body(msgRetorno);
    }
}
