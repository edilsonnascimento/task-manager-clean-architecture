package br.ednascimento.taskmanager.application.service;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;
import br.ednascimento.taskmanager.application.port.out.TaskRepositoryPort;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidRepositoryPortException;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;

import java.util.Objects;

public class CreateTaskService {

    private final TaskRepositoryPort repository;

    public CreateTaskService(TaskRepositoryPort repository) {
        validateRepository(repository);
        this.repository = Objects.requireNonNull(repository);
    }

    private void validateRepository(TaskRepositoryPort repository) {
        if (Objects.isNull(repository))
            throw new InvalidRepositoryPortException("repository null");
    }

    public Long create(CreateTaskCommand command) {
        var task = new Task(command.title(), command.description());
        return repository.save(task).orElseThrow(()-> new InvalidTaskException("Task error create"));
    }
}
