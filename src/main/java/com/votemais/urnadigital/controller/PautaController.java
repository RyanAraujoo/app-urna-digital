package com.votemais.urnadigital.controller;

import com.votemais.urnadigital.controller.interfaces.PautaControllerInterface;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.service.PautaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping(value = "/pauta")
public class PautaController implements PautaControllerInterface {

    @Autowired
    private PautaService pautaService;

    @Override
    @PostMapping("/encerrar/{id}")
    public ResponseEntity<String> encerrar(@PathVariable("id") @Valid String idPauta) {
        UUID uuid = UUID.fromString(idPauta);
        String msgRetorno = pautaService.encerrar(uuid);
        return ResponseEntity.ok().body(msgRetorno);
    }

    @Override
    @PostMapping("/abrir/{id}")
    public ResponseEntity<String> abrir(@PathVariable("id") @Valid String idPauta) {
        UUID uuid = UUID.fromString(idPauta);
        String msgRetorno = pautaService.abrir(uuid);
        return ResponseEntity.ok().body(msgRetorno);
    }

    @Override
    @PostMapping("/cadastrar")
    public ResponseEntity<String> cadastrar(@RequestBody @Valid PautaDTO pauta) {
        String msgRetorno = pautaService.cadastrar(pauta);
        return ResponseEntity.ok().body(msgRetorno);
    }
}
