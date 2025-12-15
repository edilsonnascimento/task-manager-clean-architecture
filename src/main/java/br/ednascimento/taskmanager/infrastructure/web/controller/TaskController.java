package br.ednascimento.taskmanager.infrastructure.web.controller;

import br.ednascimento.taskmanager.application.usecases.DeleteTaskInteractor;
import br.ednascimento.taskmanager.domain.entity.TaskStatus;
import br.ednascimento.taskmanager.infrastructure.web.dto.*;
import br.ednascimento.taskmanager.infrastructure.web.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/tasks")
public class TaskController {

    private final CreateTaskService createTaskService;
    private final FindTasksService findTasksService;
    private final FindTaskService findTaskService;
    private final UpdateTaskService updateTaskService;
    private final DeleteTaskInteractor deleteTaskInteractor;
    private final PatchStatusService patchStatusService;

    @PostMapping
    public ResponseEntity<CreateTaskResponseDto> create(@RequestBody CreateTaskRequestDto request) throws URISyntaxException {
        return createTaskService.create(request);
    }

    @GetMapping
    public List<TaskResponseDto> list() {
        return findTasksService.find();
    }

    @GetMapping("/{id}")
    public TaskResponseDto findById(@PathVariable Long id) {
        return findTaskService.find(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        updateTaskRequestDto.setId(id);
        updateTaskService.update(updateTaskRequestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteTaskInteractor.delete(id);
    }

    @PatchMapping("/{id}/in-progress")
    public void patchInProgress(@PathVariable Long id) {
        patchStatusService.updateInProgressStatus(id);
    }

    @PatchMapping("/{id}/done")
    public void patchDone(@PathVariable Long id) {
        patchStatusService.updateDoneStatus(id);
    }
}
