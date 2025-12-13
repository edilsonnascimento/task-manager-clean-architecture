package br.ednascimento.taskmanager.infrastructure.controller;

import br.ednascimento.taskmanager.application.usecases.CreateTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.DeleteTaskInteractor;
import br.ednascimento.taskmanager.application.usecases.FindTaskByIdInteractor;
import br.ednascimento.taskmanager.application.usecases.ListTasksInteractor;
import br.ednascimento.taskmanager.application.usecases.UpdateTaskInteractor;
import br.ednascimento.taskmanager.domain.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/tasks")
public class TaskController {

    private final CreateTaskInteractor createTask;
    private final ListTasksInteractor listTasks;
    private final FindTaskByIdInteractor findTaskById;
    private final UpdateTaskInteractor updateTask;
    private final DeleteTaskInteractor deleteTask;
    private final TaskDTOMapper mapper;

    @PostMapping
    public ResponseEntity<CreateTaskResponse> create(@RequestBody CreateTaskRequest request) throws URISyntaxException {
        var id = createTask.create(mapper.toCommand(request));
        URI uri = new URI("/v1/tasks/" + id);
        return ResponseEntity.created(uri).build();
    }

    @GetMapping
    public List<TaskResponse> list() {
        return listTasks.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public TaskResponse findById(@PathVariable Long id) {
        var taskEntity = findTaskById.finOne(id);
        return mapper.toResponse(taskEntity);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody Task task) {
        task.setId(id);
        updateTask.update(task);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteTask.delete(id);
    }
}
