package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.ListTasksInteractor;
import br.ednascimento.taskmanager.infrastructure.web.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FindTasksHttpAdapter {

    private final ListTasksInteractor listTasksInteractor;
    private final TaskDtoMapper mapper;

    public List<TaskResponseDto> find() {
        return listTasksInteractor.findAll().stream()
                .map(mapper::TaskDomainToTaskResponse)
                .toList();
    }
}
