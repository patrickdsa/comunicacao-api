package com.luizalebs.comunicacao_api.api.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class EmailRequestDTOFixture {

    public static EmailRequestDTO build(
            String nomeDestinatario,
            String mensagem,
            LocalDateTime dataHoraEnvio,
            String emailDestinatario
    ) {
        return new EmailRequestDTO(nomeDestinatario, mensagem, dataHoraEnvio, emailDestinatario);

    }
}
