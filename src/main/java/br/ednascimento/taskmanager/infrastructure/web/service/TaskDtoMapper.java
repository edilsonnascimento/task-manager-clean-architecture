package br.ednascimento.taskmanager.infrastructure.web.service;

import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.infrastructure.web.dto.CreateTaskRequestDto;
import br.ednascimento.taskmanager.infrastructure.web.dto.TaskResponseDto;
import br.ednascimento.taskmanager.infrastructure.web.dto.UpdateTaskRequestDto;
import org.springframework.stereotype.Component;

@Component
public class TaskDtoMapper {

    public Task createTaskRequestDtoToTaskDomain(CreateTaskRequestDto createTaskRequestDto) {
        return new Task(createTaskRequestDto.title(), createTaskRequestDto.description());
    }

    public TaskResponseDto TaskDomainToTaskResponse(Task taskDomain) {
        return new TaskResponseDto(taskDomain.getTitle(), taskDomain.getDescription());
    }

    public Task updateTaskRequestDtoToTaskDomain(UpdateTaskRequestDto updateTaskRequestDto) {
        var taskDomain = new Task(updateTaskRequestDto.getTitle(), updateTaskRequestDto.getDescription());
        taskDomain.setId(updateTaskRequestDto.getId());
        return taskDomain;
    }
}
