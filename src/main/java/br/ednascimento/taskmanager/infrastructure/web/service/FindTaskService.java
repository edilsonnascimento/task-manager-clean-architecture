package br.ednascimento.taskmanager.infrastructure.web.service;

import br.ednascimento.taskmanager.application.usecases.CreateTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.FindTaskByIdInteractor;
import br.ednascimento.taskmanager.infrastructure.web.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FindTaskService {

    private final FindTaskByIdInteractor findTaskByIdInteractor;
    private final TaskDtoMapper mapper;

    public TaskResponseDto find(Long id) {
        var taskDomain = findTaskByIdInteractor.finOne(id);
        return mapper.TaskDomainToTaskResponse(taskDomain);
    }
}
