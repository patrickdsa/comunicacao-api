package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import com.luizalebs.comunicacao_api.infraestructure.clients.EmailClient;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.EmailException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmailService {

    private final EmailClient emailClient;
    private final ComunicacaoService comunicacaoService;

    public void enviaEmail(EmailRequestDTO dto) {

        try {
            emailClient.enviarEmail(dto);
        }catch (EmailException e){
            throw new EmailException("Falha ao enviar email para: " + dto.getEmailDestinatario());
        }
    }

}
