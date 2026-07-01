package com.luizalebs.comunicacao_api.api;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.business.service.ComunicacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comunicacao")
public class ComunicacaoController {

    private final ComunicacaoService service;

    public ComunicacaoController(ComunicacaoService service) {
        this.service = service;
    }

    @PostMapping("/agendar")
    @Operation(summary = "Agendar mensagem", description = "Agenda mensagem para ser enviada ao destinatário")
    @ApiResponse(responseCode = "200", description = "Mensagem agendada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Erro ao agendar mensagem.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<ComunicacaoOutDTO> agendar(@RequestBody ComunicacaoInDTO dto)  {
        return ResponseEntity.ok(service.agendarComunicacao(dto));
    }

    @GetMapping()
    @Operation(summary = "Buscar Status da Mensagem", description = "Busca o Status da mensagem através do email do " +
            "destinatário")
    @ApiResponse(responseCode = "200", description = "Status de mensagem encontrada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Erro ao buscar o Status da mensagem.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<ComunicacaoOutDTO> buscarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.buscarStatusComunicacao(emailDestinatario));
    }

    @PatchMapping("/cancelar")
    @Operation(summary = "Cancelar mensagem", description = "Altera o Status da mensagem para CANCELADO e aborta o " +
            "envio da mensagem ao destinatário.")
    @ApiResponse(responseCode = "200", description = "Mensagem cancelada com sucesso.")
    @ApiResponse(responseCode = "400", description = "Erro ao cancelar mensagem.")
    @ApiResponse(responseCode = "500", description = "Erro de servidor.")
    public ResponseEntity<ComunicacaoOutDTO> cancelarStatus(@RequestParam String emailDestinatario) {
        return ResponseEntity.ok(service.alterarStatusComunicacao(emailDestinatario));
    }
}
