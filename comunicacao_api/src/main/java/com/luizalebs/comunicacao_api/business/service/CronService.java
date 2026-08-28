package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import com.luizalebs.comunicacao_api.infraestructure.exceptions.EmailException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CronService {

    private final EmailService emailService;
    private final ComunicacaoService comunicacaoService;

    @Scheduled(cron = "0 */5 * * * *")
    public void buscaComunicacaoProximaHora(){
        try {LocalDateTime horaAtual = LocalDateTime.now();
        LocalDateTime horaFuturaMaisCinco = horaAtual.plusMinutes(5);
        List<EmailRequestDTO> emails = comunicacaoService.buscaComunicacaoAgendadaPorPeriodo(horaAtual,
                horaFuturaMaisCinco);
        emails.forEach(email -> {
            emailService.enviaEmail(email);
            comunicacaoService.marcarComoEnviado(email.getEmailDestinatario());
        });} catch (EmailException e){
            throw new EmailException("Erro ao enviar email" + e.getCause());
        }
    }
}