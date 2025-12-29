package br.ednascimento.taskmanager.infrastructure.web.controller;

import br.ednascimento.taskmanager.infrastructure.web.dto.*;
import br.ednascimento.taskmanager.infrastructure.web.adapter.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("v1/tasks")
public class TaskController {

    private final CreateTaskHttpAdapter createTaskHttpAdapter;
    private final FindTasksHttpAdapter findTasksHttpAdapter;
    private final FindTaskHttpAdapter findTaskHttpAdapter;
    private final UpdateTaskHttpAdapter updateTaskHttpAdapter;
    private final DeleteTaskHttpAdapter deleteTaskHttpAdapter;
    private final PatchStatusHttpAdapter patchStatusHttpAdapter;

    @PostMapping
    public ResponseEntity<CreateTaskResponseDto> create(@RequestBody CreateTaskRequestDto request) throws URISyntaxException {
        return createTaskHttpAdapter.create(request);
    }

    @GetMapping
    public List<TaskResponseDto> list() {
        return findTasksHttpAdapter.find();
    }

    @GetMapping("/{id}")
    public TaskResponseDto findById(@PathVariable Long id) {
        return findTaskHttpAdapter.find(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdateTaskRequestDto updateTaskRequestDto) {
        updateTaskRequestDto.setId(id);
        updateTaskHttpAdapter.update(updateTaskRequestDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        deleteTaskHttpAdapter.delete(id);
    }

    @PatchMapping("/{id}/in-progress")
    public void patchInProgress(@PathVariable Long id) {
        patchStatusHttpAdapter.updateInProgressStatus(id);
    }

    @PatchMapping("/{id}/done")
    public void patchDone(@PathVariable Long id) {
        patchStatusHttpAdapter.updateDoneStatus(id);
    }
}
