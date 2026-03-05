package com.votemais.urnadigital.repository.interfaces;

import com.votemais.urnadigital.domain.Pauta;
import com.votemais.urnadigital.domain.daos.PautaDAO;
import com.votemais.urnadigital.domain.dtos.PautaDTO;
import com.votemais.urnadigital.domain.enums.StatusAberturaEnum;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class PautaRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    PautaRepository pautaRepository;

    @Test
    @DisplayName("Deve Alterar o Status da Pauta com Sucesso")
    void alteraStatusPauta() {
        PautaDTO pautaDao = new PautaDTO(
                "CamaraMunicialItb",
                "Rua Josafá Vargas, 313",
                80
        );

        this.criarPauta(pautaDao);
        Optional<PautaDAO> rtnPauta = this.pautaRepository.findByNome(pautaDao.getNome());

        UUID id = rtnPauta.get().getId();

        int ret = this.pautaRepository.AlteraStatusPauta(id,StatusAberturaEnum.ABERTO);

        assertThat(ret).isEqualTo(1);

    }

    @Test
    @DisplayName("Deve Falhar ao Alterar o Status da Pauta Pois Não Existe")
    void alteraStatusPautaNãoExistente() {
        UUID id = UUID.randomUUID();

        int ret = this.pautaRepository.AlteraStatusPauta(id,StatusAberturaEnum.ABERTO);

        assertThat(ret).isEqualTo(0);

    }

    private UUID criarPauta(PautaDTO pautaDao) {
        UUID idPauta = UUID.randomUUID();

        PautaDAO pauta = new PautaDAO(
                pautaDao.getNome(),
                pautaDao.getEndereco(),
                pautaDao.getTempoEmMinutos(),
                0,
                0,
                StatusAberturaEnum.FECHADO
        );

        this.entityManager.persist(pauta);

        return idPauta;
    }
}