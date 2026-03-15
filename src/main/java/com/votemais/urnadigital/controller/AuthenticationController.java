package com.votemais.urnadigital.controller;

import com.votemais.urnadigital.domain.Associado;
import com.votemais.urnadigital.domain.records.AuthenticationDTO;
import com.votemais.urnadigital.domain.records.RegisterDTO;
import com.votemais.urnadigital.domain.records.ResponseTokenDTO;
import com.votemais.urnadigital.infra.security.TokenService;
import com.votemais.urnadigital.repository.interfaces.AssociadoRepository;
import com.votemais.urnadigital.service.AssociadoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    AssociadoService associadoService;

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid AuthenticationDTO req) {
         var userPassToken = new UsernamePasswordAuthenticationToken(req.userName(), req.password());
         var auth = this.authenticationManager.authenticate(userPassToken);
         var token = this.tokenService.CreateJWT((Associado) auth.getPrincipal());
         return ResponseEntity.ok(new ResponseTokenDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO req) {
        var msg = this.associadoService.criarAssociado(req);
        return ResponseEntity.ok().body(msg);
    }
}
