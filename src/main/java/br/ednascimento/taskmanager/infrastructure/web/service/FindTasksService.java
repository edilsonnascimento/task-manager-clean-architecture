package br.ednascimento.taskmanager.infrastructure.web.service;

import br.ednascimento.taskmanager.application.usecases.ListTasksInteractor;
import br.ednascimento.taskmanager.infrastructure.web.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FindTasksService {

    private final ListTasksInteractor listTasksInteractor;
    private final TaskDtoMapper mapper;

    public List<TaskResponseDto> find() {
        return listTasksInteractor.findAll().stream()
                .map(mapper::TaskDomainToTaskResponse)
                .toList();
    }
}
