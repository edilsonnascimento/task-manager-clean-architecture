package br.ednascimento.taskmanager.application.usecases;

import br.ednascimento.taskmanager.application.exception.InvalidCreateGatewayException;
import br.ednascimento.taskmanager.application.gateways.TaskGateway;
import br.ednascimento.taskmanager.domain.exception.InvalidTaskException;

import java.util.Objects;

public class DeleteTaskInteractor {

    private final TaskGateway gateway;

    public DeleteTaskInteractor(TaskGateway gateway) {
        requireGateway(gateway);
        this.gateway = gateway;
    }

    private void requireGateway(TaskGateway taskGateway) {
        if (Objects.isNull(taskGateway))
            throw new InvalidCreateGatewayException("error create");
    }

    public void delete(Long id) {
        validateId(id);
        gateway.findById(id).orElseThrow(() -> new InvalidTaskException("Task not found"));
        gateway.delete(id);
    }

    private void validateId(Long id) {
        if (Objects.isNull(id))
            throw new InvalidTaskException("Task id must not be null");
    }
}