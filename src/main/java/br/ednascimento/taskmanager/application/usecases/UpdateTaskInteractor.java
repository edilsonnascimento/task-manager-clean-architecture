package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.entity.Task;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;

import java.util.Objects;

public class UpdateTaskInteractor {

    private final TaskGateway gateway;

    public UpdateTaskInteractor(TaskGateway gateway) {
        validateRepository(gateway);
        this.gateway = gateway;
    }

    private void validateRepository(TaskGateway taskGateway) {
        if (Objects.isNull(taskGateway))
            throw new InvalidCreateGatewayException("error create");
    }

    public void update(Task task) {
        validateTask(task);
        gateway.findById(task.getId()).orElseThrow(() -> new InvalidTaskException("Task not found"));
        gateway.update(task);
    }

    private void validateTask(Task task) {
        if (Objects.isNull(task))
            throw new InvalidTaskException("Not created");
    }
}
