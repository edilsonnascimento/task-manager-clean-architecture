package br.ednascimento.taskmanager.infrastructure.web.dto;

import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record TaskResponseDto(
        Long id,
        String title,
        String description,
        TaskStatus taskStatus,
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
        LocalDateTime createDate) {
}
