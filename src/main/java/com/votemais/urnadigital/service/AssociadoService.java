package com.votemais.urnadigital.service;

import com.votemais.urnadigital.domain.Associado;
import com.votemais.urnadigital.domain.Voto;
import com.votemais.urnadigital.domain.dtos.AssociadoDTO;
import com.votemais.urnadigital.repository.interfaces.AssociadoRepository;
import com.votemais.urnadigital.service.interfaces.AssociadoServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AssociadoService implements AssociadoServiceInterface {

    @Autowired
    AssociadoRepository associadoRepository;

    @Override
    public String criarAssociado(AssociadoDTO associadoDto) {
        Associado novoAssociado = new Associado(
            associadoDto.getCpf(),
            associadoDto.getNome(),
            associadoDto.getEndereco(),
            associadoDto.getIdade()
        );
        associadoRepository.save(novoAssociado);
        return "Associado criado com Sucesso!";
    }


    private Associado buscarAssociado(UUID idAssociado) throws Exception {
        Optional<Associado> associado = associadoRepository.findById(idAssociado);

        if (associado.isEmpty()) {
            throw new Exception("Associado não identificado.");
        }

        return associado.get();
    }
    private void verificarVotoRealizado(UUID idPauta, Set<Voto> votosDoAssociado) throws Exception {
        Set<Voto> votoDaPauta = votosDoAssociado.stream()
                .filter(voto -> voto.getId() == idPauta)
                .collect(Collectors.toSet());

        if (votoDaPauta.isEmpty()) {
            throw new Exception("Voto já realizado para a Pauta.");
        }
    }

    @Override
    public String votar(UUID idPauta, UUID idAssociado) throws Exception {
        Associado associado = this.buscarAssociado(idAssociado);

        this.verificarVotoRealizado(idPauta, associado.getVotos());

        Voto voto = new Voto(
                idPauta,
                associado
        );

        associado.setVoto(voto);

        this.associadoRepository.AtualizarVotosDoAssociado((Voto) associado.getVotos(), idAssociado);

        return "Voto Realizado com Sucesso para a Pauta" + idPauta.toString();
    }
}
