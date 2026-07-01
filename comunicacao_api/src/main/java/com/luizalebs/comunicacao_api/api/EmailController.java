package com.luizalebs.comunicacao_api.api;


import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import com.luizalebs.comunicacao_api.business.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @PostMapping
    public ResponseEntity<Void> enviarEmail(@RequestBody EmailRequestDTO dto){
        emailService.enviaEmail(dto);
        return ResponseEntity.ok().build();
    }}

