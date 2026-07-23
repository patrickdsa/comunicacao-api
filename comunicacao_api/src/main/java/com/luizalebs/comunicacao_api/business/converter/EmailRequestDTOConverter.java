package com.luizalebs.comunicacao_api.business.converter;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class EmailRequestDTOConverter {

    public EmailRequestDTO paraEmailDTO(ComunicacaoOutDTO dto) {
        return EmailRequestDTO.builder()
                .emailDestinatario(dto.getEmailDestinatario())
                .nomeDestinatario(dto.getNomeDestinatario())
                .mensagem(dto.getMensagem())
                .dataHoraEnvio(dto.getDataHoraEnvio())
                .build();
    }

    public ComunicacaoOutDTO paraComunicacaoOutDTO(EmailRequestDTO dto) {
        return ComunicacaoOutDTO.builder()
                .emailDestinatario(dto.getEmailDestinatario())
                .nomeDestinatario(dto.getNomeDestinatario())
                .mensagem(dto.getMensagem())
                .dataHoraEnvio(dto.getDataHoraEnvio())
                .build();
    }


}
