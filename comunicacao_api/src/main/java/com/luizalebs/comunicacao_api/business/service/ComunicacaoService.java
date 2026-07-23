package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoConverter;
import com.luizalebs.comunicacao_api.business.converter.EmailRequestDTOConverter;
import com.luizalebs.comunicacao_api.infraestructure.entities.ComunicacaoEntity;
import com.luizalebs.comunicacao_api.infraestructure.enums.StatusEnvioEnum;
import com.luizalebs.comunicacao_api.infraestructure.repositories.ComunicacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ComunicacaoService {

    private final ComunicacaoRepository repository;
    private final ComunicacaoConverter converter;
    private final EmailRequestDTOConverter emailRequestDTOConverter;

    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = converter.paraEntity(dto);
        repository.save(entity);
        ComunicacaoOutDTO outDTO = converter.paraDTO(entity);
        return outDTO;
    }

    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        return converter.paraDTO(entity);
    }

    public ComunicacaoOutDTO alterarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        entity.setStatusEnvio(StatusEnvioEnum.CANCELADO);
        repository.save(entity);
        return (converter.paraDTO(entity));
    }

    public List<EmailRequestDTO> buscaComunicacaoAgendadaPorPeriodo(LocalDateTime horaInicial,
                                                                     LocalDateTime horaFinal) {
        List<ComunicacaoEntity> entities = repository.findByStatusEnvioAndDataHoraEnvioBetween(
                StatusEnvioEnum.PENDENTE, horaInicial, horaFinal);
        if (Objects.isNull(entities)) {
            throw new RuntimeException();
        }
        return entities.stream()
                .map(converter::paraDTO)
                .map(emailRequestDTOConverter::paraEmailDTO)
                .collect(Collectors.toList());
    }

    public void marcarComoEnviado(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        entity.setStatusEnvio(StatusEnvioEnum.ENVIADO);
        repository.save(entity);
    }

}
