package com.luizalebs.comunicacao_api.infraestructure.clients;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificacao", url="${email-api.url}")
public interface EmailClient {

    @PostMapping("/email/comunicacao")
    void enviarEmail(@RequestBody EmailRequestDTO dto);
}

