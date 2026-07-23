package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import com.luizalebs.comunicacao_api.business.converter.EmailRequestDTOConverter;
import com.luizalebs.comunicacao_api.infraestructure.clients.EmailClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailClient emailClient;
    private final EmailRequestDTOConverter emailConverter;
    private final ComunicacaoService comunicacaoService;

    public void enviaEmail(EmailRequestDTO dto) {
        emailClient.enviarEmail(dto);
    }

}
