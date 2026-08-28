package com.luizalebs.comunicacao_api.business.service;

import com.luizalebs.comunicacao_api.api.dto.ComunicacaoInDTO;
import com.luizalebs.comunicacao_api.api.dto.ComunicacaoOutDTO;
import com.luizalebs.comunicacao_api.api.dto.EmailRequestDTO;
import com.luizalebs.comunicacao_api.business.converter.ComunicacaoMapper;
import com.luizalebs.comunicacao_api.business.converter.EmailMapper;
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
    private final ComunicacaoMapper comunicacaoMapper;
    private final EmailMapper emailMapper;

    public ComunicacaoOutDTO agendarComunicacao(ComunicacaoInDTO dto) {
        if (Objects.isNull(dto)) {
            throw new RuntimeException();
        }
        dto.setStatusEnvio(StatusEnvioEnum.PENDENTE);
        ComunicacaoEntity entity = comunicacaoMapper.paraComunicacaoEntity(dto);
        repository.save(entity);
        ComunicacaoOutDTO outDTO = comunicacaoMapper.paraComunicacaoOutDTO(entity);
        return outDTO;
    }

    public ComunicacaoOutDTO buscarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        return comunicacaoMapper.paraComunicacaoOutDTO(entity);
    }

    public ComunicacaoOutDTO alterarStatusComunicacao(String emailDestinatario) {
        ComunicacaoEntity entity = repository.findByEmailDestinatario(emailDestinatario);
        if (Objects.isNull(entity)) {
            throw new RuntimeException();
        }
        entity.setStatusEnvio(StatusEnvioEnum.CANCELADO);
        repository.save(entity);
        return (comunicacaoMapper.paraComunicacaoOutDTO(entity));
    }

    public List<EmailRequestDTO> buscaComunicacaoAgendadaPorPeriodo(LocalDateTime horaInicial,
                                                                    LocalDateTime horaFinal) {
        List<ComunicacaoEntity> entities = repository.findByStatusEnvioAndDataHoraEnvioBetween(
                StatusEnvioEnum.PENDENTE, horaInicial, horaFinal);
        if (Objects.isNull(entities)) {
            throw new RuntimeException();
        }
        return entities.stream()
                .map(comunicacaoMapper::paraComunicacaoOutDTO)
                .map(emailMapper::paraEmailRequestDTO)
                .collect(Collectors.toList());
    }

    public List<EmailRequestDTO> buscaComunicacaoPendenteAteAgora(LocalDateTime horaLimite) {
        List<ComunicacaoEntity> entities =
                repository.findByStatusEnvioAndDataHoraEnvioLessThanEqual(StatusEnvioEnum.PENDENTE, horaLimite);
        if (Objects.isNull(entities)) {
            throw new RuntimeException();
        }
        return entities.stream()
                .map(comunicacaoMapper::paraComunicacaoOutDTO)
                .map(emailMapper::paraEmailRequestDTO)
                .collect(Collectors.toList());
    }
}
