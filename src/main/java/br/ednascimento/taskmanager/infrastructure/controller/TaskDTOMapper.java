package br.ednascimento.taskmanager.infrastructure.controller;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;
import br.ednascimento.taskmanager.domain.entity.Task;

public class TaskDTOMapper {

    public CreateTaskCommand toCommand(CreateTaskRequest request) {
        return new CreateTaskCommand(request.title(), request.description());
    }

    public TaskResponse toResponse(Task taskEntity) {
        return new TaskResponse(taskEntity.getTitle(), taskEntity.getDescription());
    }
}
