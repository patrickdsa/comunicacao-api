package com.luizalebs.comunicacao_api.infraestructure.repositories;

import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ComunicacaoRepository extends JpaRepository<ComunicacaoEntity, Long> {

    ComunicacaoEntity findByEmailDestinatario(String nomeDestinatario);

    List<ComunicacaoEntity> findByStatusEnvioAndDataHoraEnvioBetween(StatusEnvioEnum statusEnvio,
                                                                LocalDateTime dataInicial,
                                                                LocalDateTime dataFinal);


    List<ComunicacaoEntity> findByStatusEnvioAndDataHoraEnvioLessThanEqual(StatusEnvioEnum statusEnvio,
                                                                           LocalDateTime dataLimite);
}
