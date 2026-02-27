package com.votemais.urnadigital.service;

import com.votemais.urnadigital.domain.Pauta;
import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import com.votemais.urnadigital.repository.interfaces.PautaRepository;
import com.votemais.urnadigital.service.interfaces.PautaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PautaService implements PautaServiceInterface {

    @Autowired
    private PautaRepository pautaRepository;

    @Override
    public String encerrar(String idPauta) {
        // Atualizar o Banco com o Fechamento da Pauta
        // Metodo Private de Contabilizar
        // Retornar Quantidade de Sim e Não
        return "A pauta solicitada foi encerrada. Segue o retorno: " + "";
    }

    @Override
    public String abrir(String idPauta) {
        pautaRepository.abrir(idPauta);
        return "A pauta solicitada foi aberta; boa sessão!";
    }

    @Override
    public String cadastrar(PautaDTO pauta) {
        PautaDAO novaPauta = new PautaDAO(
                UUID.randomUUID(),
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
