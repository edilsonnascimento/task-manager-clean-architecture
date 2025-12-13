package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidRepositoryPortException;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;

import java.util.Objects;

public class CreateTaskInteractor {

    private final TaskGateway repository;

    public CreateTaskInteractor(TaskGateway repository) {
        validateRepository(repository);
        this.repository = Objects.requireNonNull(repository);
    }

    private void validateRepository(TaskGateway repository) {
        if (Objects.isNull(repository))
            throw new InvalidRepositoryPortException("repository null");
    }

    public Long create(CreateTaskCommand command) {
        var task = new Task(command.title(), command.description());
        return repository.save(task).orElseThrow(()-> new InvalidTaskException("Task error create"));
    }
}
