package br.ednascimento.taskmanager.infrastructure.web.adapter;

import br.ednascimento.taskmanager.application.usecases.CreateTaskInteractor;
import br.ednascimento.taskmanager.infrastructure.web.dto.CreateTaskRequestDto;
import br.ednascimento.taskmanager.infrastructure.web.dto.CreateTaskResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;

@RequiredArgsConstructor
@Component
public class CreateTaskHttpAdapter {

    private final CreateTaskInteractor createTaskInteractor;
    private final TaskDtoMapper mapper;

    public ResponseEntity<CreateTaskResponseDto> create(CreateTaskRequestDto request) throws URISyntaxException {
        var taskDomain = mapper.createTaskRequestDtoToTaskDomain(request);
        var id = createTaskInteractor.create(taskDomain);
        URI uri = new URI("/v1/tasks/" + id);
        return ResponseEntity.created(uri).build();
    }
}
