package com.votemais.urnadigital.service;

import com.votemais.urnadigital.domain.Associado;
import com.votemais.urnadigital.domain.Pauta;
import com.votemais.urnadigital.domain.Voto;
import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.domain.exceptions.NotFoundException;
import com.votemais.urnadigital.domain.records.RegisterDTO;
import com.votemais.urnadigital.repository.interfaces.AssociadoRepository;
import com.votemais.urnadigital.repository.interfaces.PautaRepository;
import com.votemais.urnadigital.service.interfaces.AssociadoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssociadoService implements AssociadoServiceInterface {

    @Autowired
    AssociadoRepository associadoRepository;

    @Autowired
    PautaRepository pautaRepository;

    @Override
    public String criarAssociado(RegisterDTO associadoDto) {
        if (this.associadoRepository.findByLogin(associadoDto.userName()) != null) { throw  new RuntimeException("Usuario já existe.");}

        var pass = new BCryptPasswordEncoder().encode(associadoDto.password());

        Associado associado = new Associado(
                associadoDto.cpf(),
                associadoDto.nome(),
                associadoDto.endereco(),
                associadoDto.idade(),
                pass,
                associadoDto.userName(),
                associadoDto.role()
        );

        this.associadoRepository.save(associado);

        return "Associado criado com Sucesso!";
    }


    private Associado buscarAssociado(UUID idAssociado) throws Exception {
        Optional<Associado> associado = associadoRepository.findById(idAssociado);

        if (associado.isEmpty()) {
            throw new NotFoundException("Associado não identificado.");
        }

        return associado.get();
    }
    private void verificarVotoRealizado(UUID idPauta, Set<Voto> votosDoAssociado) throws Exception {
        Set<Voto> votoDaPauta = votosDoAssociado.stream()
                .filter(voto -> voto.getVotoId().equals(idPauta))
                .collect(Collectors.toSet());

        if (!votoDaPauta.isEmpty()) {
            throw new Exception("Voto já realizado para a Pauta.");
        }
    }

    @Override
    public String votar(UUID idPauta, UUID idAssociado) throws Exception {
        Associado associado = this.buscarAssociado(idAssociado);
        Optional<PautaDAO> pauta = this.pautaRepository.findById(idPauta);

        this.verificarVotoRealizado(idPauta, associado.getVotos());

        Voto voto = new Voto(
                pauta.get(),
                associado
        );

        associado.setVoto(voto);

        this.associadoRepository.save(associado);

        return "Voto Realizado com Sucesso para a Pauta" + idPauta.toString();
    }
}
