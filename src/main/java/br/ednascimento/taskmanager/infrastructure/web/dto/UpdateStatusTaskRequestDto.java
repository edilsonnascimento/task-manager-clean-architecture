package br.ednascimento.taskmanager.infrastructure.web.dto;

import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public  class UpdateStatusTaskRequestDto {

    private Long id;
    private TaskStatus status;
}