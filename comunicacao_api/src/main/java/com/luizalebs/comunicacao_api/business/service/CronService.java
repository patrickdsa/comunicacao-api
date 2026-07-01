package com.luizalebs.comunicacao_api.business.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CronService {
     private final EmailService emailService;
     private final ComunicacaoService comunicacaoService;

     public void buscarComunicacaPorPeriodo
}
