package com.votemais.urnadigital.service;

import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.FechamentoUrnaDTO;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import com.votemais.urnadigital.repository.interfaces.PautaRepository;
import com.votemais.urnadigital.service.interfaces.PautaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class PautaService implements PautaServiceInterface {

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public String encerrar(UUID idPauta) {
        pautaRepository.AlteraStatusPauta(idPauta, StatusAberturaEnum.FECHADO);
        Optional<PautaDAO> pauta = pautaRepository.findById(idPauta);

        FechamentoUrnaDTO fechamentoPauta = new FechamentoUrnaDTO(
                pauta.get().getQuantidadeDeVotosSim(),
                pauta.get().getQuantidadeDeVotosNao()
        );

        return "A pauta solicitada foi encerrada. Segue o retorno: " + fechamentoPauta.returnFechamento() ;
    }

    @Override
    public String abrir(UUID idPauta) {
        pautaRepository.AlteraStatusPauta(idPauta, StatusAberturaEnum.ABERTO);
        return "A pauta solicitada foi aberta; boa sessão!";
    }

    @Override
    public String cadastrar(PautaDTO pauta) {
        PautaDAO novaPauta = new PautaDAO(
                pauta.getNome(),
                pauta.getEndereco(),
                pauta.getTempoEmMinutos(),
                0,
                0,
                StatusAberturaEnum.FECHADO
        );
        pautaRepository.save(novaPauta);
        return "Pauta + " + pauta.getNome() + "Salva com sucesso";
    }
}
