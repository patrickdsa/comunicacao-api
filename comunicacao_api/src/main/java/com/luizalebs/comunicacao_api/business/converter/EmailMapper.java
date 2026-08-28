package com.luizalebs.comunicacao_api.business.converter;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface EmailMapper {

    ComunicacaoOutDTO paraComunicacaoOutDTO (EmailRequestDTO emailRequestDTO);

    EmailRequestDTO paraEmailRequestDTO (ComunicacaoOutDTO comunicacaoOutDTO);
}
