package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.dto.CreateTaskCommand;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;
import br.ednascimento.taskmanager.infrastructure.persistence.TaskEntity;

import java.util.Objects;

public class CreateTaskInteractor {

    private final TaskGateway taskGateway;

    public CreateTaskInteractor(TaskGateway taskGateway) {
        validateRepository(taskGateway);
        this.taskGateway = Objects.requireNonNull(taskGateway);
    }

    private void validateRepository(TaskGateway taskGateway) {
        if (Objects.isNull(taskGateway))
            throw new InvalidCreateGatewayException("error create");
    }

    public Long create(Task task) {
        return taskGateway.save(task).orElseThrow(()-> new InvalidTaskException("Task error create"));
    }
}
