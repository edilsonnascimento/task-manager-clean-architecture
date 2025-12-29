package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.UpdateTaskInteractor;
import br.ednascimento.taskmanager.infrastructure.web.dto.UpdateTaskRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class UpdateTaskHttpAdapter {

    private final UpdateTaskInteractor updateTaskInteractor;
    private final TaskDtoMapper mapper;

    public void update(UpdateTaskRequestDto updateTaskRequestDto) {
        var taskDomain = mapper.updateTaskRequestDtoToTaskDomain(updateTaskRequestDto);
        updateTaskInteractor.update(taskDomain);
    }
}
