package com.luizalebs.comunicacao_api.api.mapper;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoMapper;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.ModoEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ComunicacaoMapperTest {

    ComunicacaoMapper comunicacaoMapper;

    ComunicacaoEntity comunicacaoEntity;

    @BeforeEach
    public void setup() {
        comunicacaoMapper = Mappers.getMapper(ComunicacaoMapper.class);

        comunicacaoEntity = ComunicacaoEntity.builder()
                .id(1234L)
                .dataHoraEnvio(LocalDateTime.now())
                .nomeDestinatario("Patrick")
                .emailDestinatario("patrick@email.com")
                .telefoneDestinatario("9999-9999")
                .mensagem("Olá Patrick Teste")
                .modoDeEnvio(ModoEnvioEnum.EMAIL)
                .statusEnvio(StatusEnvioEnum.ENVIADO)
                .build();
    }

    @Test
    void deveConverterParaComunicacaoOutDTO() {
        ComunicacaoOutDTO dto = comunicacaoMapper.paraComunicacaoOutDTO(comunicacaoEntity);

        assertNotNull(dto);
        assertEquals(comunicacaoEntity.getDataHoraEnvio(), dto.getDataHoraEnvio());
        assertEquals(comunicacaoEntity.getNomeDestinatario(), dto.getNomeDestinatario());
        assertEquals(comunicacaoEntity.getEmailDestinatario(), dto.getEmailDestinatario());
        assertEquals(comunicacaoEntity.getTelefoneDestinatario(), dto.getTelefoneDestinatario());
        assertEquals(comunicacaoEntity.getMensagem(), dto.getMensagem());
        assertEquals(comunicacaoEntity.getModoDeEnvio(), dto.getModoDeEnvio());
        assertEquals(comunicacaoEntity.getStatusEnvio(), dto.getStatusEnvio());
    }
}