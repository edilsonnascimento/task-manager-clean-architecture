package br.ednascimento.taskmanager.infrastructure.web.dto;

import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import lombok.Data;

@Data
public  class UpdateTaskRequestDto {

    private Long id;
    private String title;
    private String description;
}