package br.ednascimento.taskmanager.infrastructure.web.dto;

import lombok.Data;

@Data
public  class UpdateTaskRequestDto {

    private Long id;
    private String title;
    private String description;
}