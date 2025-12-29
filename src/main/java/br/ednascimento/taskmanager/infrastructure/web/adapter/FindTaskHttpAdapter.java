package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.FindTaskByIdInteractor;
import br.ednascimento.taskmanager.infrastructure.web.dto.TaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Component
public class FindTaskHttpAdapter {

    private final FindTaskByIdInteractor findTaskByIdInteractor;
    private final TaskDtoMapper mapper;

    public TaskResponseDto find(Long id) {
        var taskDomain = findTaskByIdInteractor.finOne(id);
        return mapper.TaskDomainToTaskResponse(taskDomain);
    }
}
