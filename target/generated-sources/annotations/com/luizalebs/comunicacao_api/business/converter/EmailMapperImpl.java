package com.luizalebs.comunicacao_api.business.converter;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-07-24T10:43:28+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.19 (Azul Systems, Inc.)"
)
@Component
public class EmailMapperImpl implements EmailMapper {

    @Override
    public ComunicacaoOutDTO paraComunicacaoOutDTO(EmailRequestDTO emailRequestDTO) {
        if ( emailRequestDTO == null ) {
            return null;
        }

        ComunicacaoOutDTO.ComunicacaoOutDTOBuilder comunicacaoOutDTO = ComunicacaoOutDTO.builder();

        comunicacaoOutDTO.dataHoraEnvio( emailRequestDTO.getDataHoraEnvio() );
        comunicacaoOutDTO.nomeDestinatario( emailRequestDTO.getNomeDestinatario() );
        comunicacaoOutDTO.emailDestinatario( emailRequestDTO.getEmailDestinatario() );
        comunicacaoOutDTO.mensagem( emailRequestDTO.getMensagem() );

        return comunicacaoOutDTO.build();
    }

    @Override
    public EmailRequestDTO paraEmailRequestDTO(ComunicacaoOutDTO comunicacaoOutDTO) {
        if ( comunicacaoOutDTO == null ) {
            return null;
        }

        EmailRequestDTO.EmailRequestDTOBuilder emailRequestDTO = EmailRequestDTO.builder();

        emailRequestDTO.nomeDestinatario( comunicacaoOutDTO.getNomeDestinatario() );
        emailRequestDTO.mensagem( comunicacaoOutDTO.getMensagem() );
        emailRequestDTO.dataHoraEnvio( comunicacaoOutDTO.getDataHoraEnvio() );
        emailRequestDTO.emailDestinatario( comunicacaoOutDTO.getEmailDestinatario() );

        return emailRequestDTO.build();
    }
}
